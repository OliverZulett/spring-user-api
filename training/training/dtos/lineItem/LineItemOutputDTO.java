package com.milankas.training.dtos.lineItem;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LineItemOutputDTO {

    private UUID id;

    private Integer qty;

}
