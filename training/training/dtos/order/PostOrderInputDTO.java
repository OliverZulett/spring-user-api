package com.milankas.training.dtos.order;

import com.milankas.training.dtos.user.PostAddressInputDTO;
import com.milankas.training.dtos.lineItem.PostLineItemInputDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PostOrderInputDTO {

    private UUID id;

    @NotNull(message = "User ID is required")
    private UUID userId;

    @Valid
    @NotNull(message = "Item lines are required")
    @Size(min=1, max=10, message = "Order must have at least one line item")
    private List<PostLineItemInputDTO> lineItems;

    @NotNull(message = "Email address is required")
    @Email(message = "Invalid email address")
    private String emailAddress;

    @Valid
    @NotNull(message = "Shop address is required")
    private PostAddressInputDTO shopAddress;

}
