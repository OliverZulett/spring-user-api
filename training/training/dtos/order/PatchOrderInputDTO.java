package com.milankas.training.dtos.order;

import com.milankas.training.dtos.user.PatchAddressInputDTO;
import com.milankas.training.dtos.lineItem.PatchLineItemInputDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PatchOrderInputDTO {

    private UUID id;

    private UUID userId;

    @Valid
    @Size(min=1, max=10, message = "Order must have at least one line item")
    private List<PatchLineItemInputDTO> lineItems;

    @Email(message = "Invalid email address")
    private String emailAddress;

    @Valid
    private PatchAddressInputDTO shopAddress;

}
