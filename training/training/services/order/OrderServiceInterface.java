package com.milankas.training.services.order;

import com.milankas.training.dtos.order.PatchOrderInputDTO;
import com.milankas.training.dtos.order.PostOrderInputDTO;
import com.milankas.training.dtos.order.OrderOutputDTO;

import java.util.List;
import java.util.UUID;

public interface OrderServiceInterface {

    List<OrderOutputDTO> findAllOrders();

    OrderOutputDTO findOrderById(UUID id);

    OrderOutputDTO saveOrder(PostOrderInputDTO product);

    OrderOutputDTO updateOrderById(UUID id, PatchOrderInputDTO productForUpdate);

    Boolean deleteOrderById(UUID id);

}
