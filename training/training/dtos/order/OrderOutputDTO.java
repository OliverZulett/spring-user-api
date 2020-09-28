package com.milankas.training.dtos.order;

import com.milankas.training.dtos.user.AddressOutputDTO;
import com.milankas.training.dtos.lineItem.LineItemOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderOutputDTO {

    private UUID id;

    private UUID userId;

    private List<LineItemOutputDTO> lineItems;

    private String emailAddress;

    private AddressOutputDTO shopAddress;

}
