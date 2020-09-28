package com.milankas.training.services.order;

import com.milankas.training.Mappers.order.OrderMapperInterface;
import com.milankas.training.dtos.order.PatchOrderInputDTO;
import com.milankas.training.dtos.order.PostOrderInputDTO;
import com.milankas.training.dtos.order.OrderOutputDTO;
import com.milankas.training.persistance.entities.LineItemEntity;
import com.milankas.training.persistance.entities.OrderEntity;
import com.milankas.training.persistance.repositories.OrderRepository;
import com.milankas.training.services.address.AddressServiceInterface;
import com.milankas.training.services.lineItem.LineItemServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService implements OrderServiceInterface {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderMapperInterface orderMapper;
    @Autowired
    AddressServiceInterface addressService;
    @Autowired
    LineItemServiceInterface lineItemService;

    @Override
    public List<OrderOutputDTO> findAllOrders() {
        List<OrderEntity> ordersReceived = orderRepository.findAll();
        List<OrderOutputDTO> ordersToSend = new ArrayList();
        ordersReceived.forEach(order -> {
            ordersToSend.add(orderMapper.EntityToDto(order));
        });
        return ordersToSend;
    }

    @Override
    public OrderOutputDTO findOrderById(UUID id) {
        return orderMapper.EntityToDto(orderRepository.findById(id).orElse(null));
    }

    @Override
    public OrderOutputDTO saveOrder(@Valid PostOrderInputDTO order) {
        OrderEntity orderToSave = orderMapper.PostDtoToEntity(order);
        orderToSave.setShopAddress(addressService.validatePostAddressToSave(order.getShopAddress()));
        orderToSave.setLineItems(lineItemService.validatePostLineItemsToSave(order.getLineItems()));
        return orderMapper.EntityToDto(orderRepository.save(orderToSave));
    }

    @Override
    public OrderOutputDTO updateOrderById(UUID id, @Valid PatchOrderInputDTO order) {
        OrderEntity orderFound = orderRepository.findById(id).orElse(null);
        if (orderFound == null) return null;
        AddressEntity addressForUpdate = addressService.validatePatchAddressToSave(orderFound.getShopAddress(), order.getShopAddress());
        List<LineItemEntity> lineItemsForUpdate = lineItemService.validatePatchLineItemsToSave(orderFound.getLineItems(), order.getLineItems());
        OrderEntity orderToUpdate = orderMapper.PatchDtoToEntity(orderFound, order);
        orderToUpdate.setShopAddress(addressForUpdate);
        orderToUpdate.setLineItems(lineItemsForUpdate);
        return orderMapper.EntityToDto(orderRepository.save(orderToUpdate));
    }

    @Override
    public Boolean deleteOrderById(UUID id) {
        OrderEntity orderFound = orderRepository.findById(id).orElse( null);
        if (orderFound == null) return null;
        orderRepository.delete(orderFound);
        return true;
    }

}
