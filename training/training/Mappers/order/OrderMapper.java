package com.milankas.training.Mappers.order;

import com.milankas.training.dtos.order.PatchOrderInputDTO;
import com.milankas.training.dtos.order.PostOrderInputDTO;
import com.milankas.training.dtos.order.OrderOutputDTO;
import com.milankas.training.persistance.entities.OrderEntity;

public class OrderMapper implements OrderMapperInterface {

    @Override
    public OrderOutputDTO EntityToDto(OrderEntity orderEntity) {
        return OrderMapperInterface.MAPPER.EntityToDto(orderEntity);
    }

    @Override
    public OrderEntity PostDtoToEntity(PostOrderInputDTO orderDTO) {
        return OrderMapperInterface.MAPPER.PostDtoToEntity(orderDTO);
    }

    @Override
    public OrderEntity PatchDtoToEntity(OrderEntity orderEntity, PatchOrderInputDTO orderDTO) {
        return null;
    }

}
