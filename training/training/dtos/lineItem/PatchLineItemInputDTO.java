package com.milankas.training.dtos.lineItem;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.UUID;

@Getter
@Setter
public class PatchLineItemInputDTO {

    private UUID id;

    @Min(value = 1, message = "Minimum quantity 1")
    private Integer qty;

}
