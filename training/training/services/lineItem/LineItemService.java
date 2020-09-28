package com.milankas.training.services.lineItem;

import com.milankas.training.Mappers.lineItem.LineItemMapperInterface;
import com.milankas.training.dtos.lineItem.PatchLineItemInputDTO;
import com.milankas.training.dtos.lineItem.PostLineItemInputDTO;
import com.milankas.training.persistance.entities.LineItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LineItemService implements LineItemServiceInterface{

    @Autowired
    LineItemMapperInterface lineItemMapper;

    @Override
    public List<LineItemEntity> validatePostLineItemsToSave(List<PostLineItemInputDTO> lineItems) {
        List<LineItemEntity> lineItemEntities = new ArrayList<>();
        lineItems.forEach((PostLineItemInputDTO lineItem) -> {
            lineItemEntities.add(lineItemMapper.PostDtoToEntity(lineItem));
        });
        return lineItemEntities;
    }

    @Override
    public List<LineItemEntity> validatePatchLineItemsToSave(List<LineItemEntity> lineItems, List<PatchLineItemInputDTO> lineItemsForUpdate) {
        if(lineItemsForUpdate == null) return lineItems;
        lineItems.clear();
        lineItemsForUpdate.forEach((PatchLineItemInputDTO lineItem) -> {
            lineItems.add(lineItemMapper.PatchDtoToEntity(lineItem));
        });
        return lineItems;
    }

}
