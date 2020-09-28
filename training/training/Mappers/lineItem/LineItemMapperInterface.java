package com.milankas.training.Mappers.lineItem;

import com.milankas.training.dtos.lineItem.LineItemOutputDTO;
import com.milankas.training.dtos.lineItem.PatchLineItemInputDTO;
import com.milankas.training.dtos.lineItem.PostLineItemInputDTO;
import com.milankas.training.persistance.entities.LineItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LineItemMapperInterface {

    LineItemMapperInterface MAPPER = Mappers.getMapper( LineItemMapperInterface.class );

    LineItemOutputDTO EntityToDto(LineItemEntity lineItemEntity);

    PatchLineItemInputDTO EntityToPatchDto(LineItemEntity lineItemEntity);

    LineItemEntity PostDtoToEntity(PostLineItemInputDTO lineItemDTO);

    LineItemEntity PatchDtoToEntity(PatchLineItemInputDTO lineItemDTO);

}
