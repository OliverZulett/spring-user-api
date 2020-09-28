package com.milankas.training.Mappers.order;

import com.milankas.training.Mappers.address.AddressMapper;
import com.milankas.training.Mappers.address.AddressMapperInterface;
import com.milankas.training.Mappers.lineItem.LineItemMapper;
import com.milankas.training.Mappers.lineItem.LineItemMapperInterface;
import com.milankas.training.dtos.user.AddressOutputDTO;
import com.milankas.training.dtos.lineItem.LineItemOutputDTO;
import com.milankas.training.dtos.order.PatchOrderInputDTO;
import com.milankas.training.dtos.order.PostOrderInputDTO;
import com.milankas.training.dtos.order.OrderOutputDTO;
import com.milankas.training.persistance.entities.LineItemEntity;
import com.milankas.training.persistance.entities.OrderEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapperInterface {

    OrderMapperInterface MAPPER = Mappers.getMapper( OrderMapperInterface.class );
    AddressMapperInterface addressMapper = new AddressMapper();
    LineItemMapperInterface lineItemMapper = new LineItemMapper();

    @Mappings({
            @Mapping(target = "shopAddress", source = "orderEntity", qualifiedByName = "addressEntityToDTO"),
            @Mapping(target = "lineItems", source = "orderEntity", qualifiedByName = "lineItemEntityToDTO")
    })
    OrderOutputDTO EntityToDto(OrderEntity orderEntity);

    OrderEntity PostDtoToEntity(PostOrderInputDTO orderDTO);

    @BeforeMapping
    default void validateFields(@MappingTarget OrderEntity orderEntity, PatchOrderInputDTO orderDTO) {
        orderDTO.setId(orderEntity.getId());
        if (orderDTO.getUserId() == null) orderDTO.setUserId(orderEntity.getUserId());
        if (orderDTO.getEmailAddress() == null) orderDTO.setEmailAddress(orderEntity.getEmailAddress());
    }
    OrderEntity PatchDtoToEntity(@MappingTarget OrderEntity orderEntity, PatchOrderInputDTO orderDTO);

    @Named("addressEntityToDTO")
    public static AddressOutputDTO addressEntityToDTO(OrderEntity order) {
        return addressMapper.EntityToDto(order.getShopAddress());
    }

    @Named("lineItemEntityToDTO")
    public static List<LineItemOutputDTO> lineItemEntityToDTO(OrderEntity order) {
        List<LineItemOutputDTO> lineItems = new ArrayList<>();
        order.getLineItems().forEach((LineItemEntity lineItem) -> {
            lineItems.add(lineItemMapper.EntityToDto(lineItem));
        });
        return lineItems;
    }

}
