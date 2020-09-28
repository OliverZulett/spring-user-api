package com.milankas.training.dtos.lineItem;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class PostLineItemInputDTO {

    private UUID id;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Minimum quantity 1")
    private Integer qty;

}
