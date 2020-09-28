package com.milankas.training.Mappers.lineItem;

import com.milankas.training.dtos.lineItem.LineItemOutputDTO;
import com.milankas.training.dtos.lineItem.PatchLineItemInputDTO;
import com.milankas.training.dtos.lineItem.PostLineItemInputDTO;
import com.milankas.training.persistance.entities.LineItemEntity;

public class LineItemMapper implements LineItemMapperInterface {

    @Override
    public LineItemOutputDTO EntityToDto(LineItemEntity lineItemEntity) {
        return LineItemMapperInterface.MAPPER.EntityToDto(lineItemEntity);
    }

    @Override
    public PatchLineItemInputDTO EntityToPatchDto(LineItemEntity lineItemEntity) {
        return LineItemMapperInterface.MAPPER.EntityToPatchDto(lineItemEntity);
    }

    @Override
    public LineItemEntity PostDtoToEntity(PostLineItemInputDTO lineItemDTO) {
        return LineItemMapperInterface.MAPPER.PostDtoToEntity(lineItemDTO);
    }

    @Override
    public LineItemEntity PatchDtoToEntity(PatchLineItemInputDTO lineItemDTO) {
        return LineItemMapperInterface.MAPPER.PatchDtoToEntity(lineItemDTO);
    }

}