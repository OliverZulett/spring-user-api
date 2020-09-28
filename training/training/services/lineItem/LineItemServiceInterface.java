package com.milankas.training.services.lineItem;

import com.milankas.training.dtos.lineItem.PatchLineItemInputDTO;
import com.milankas.training.dtos.lineItem.PostLineItemInputDTO;
import com.milankas.training.persistance.entities.LineItemEntity;

import java.util.List;

public interface LineItemServiceInterface {

    List<LineItemEntity> validatePostLineItemsToSave(List<PostLineItemInputDTO> lineItems);

    List<LineItemEntity> validatePatchLineItemsToSave(List<LineItemEntity> lineItems, List<PatchLineItemInputDTO> lineItemsForUpdate);
}
