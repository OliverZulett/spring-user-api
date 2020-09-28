package com.milankas.training.services.user;

import com.milankas.training.dtos.user.PatchUserInputDTO;
import com.milankas.training.dtos.user.PostUserInputDTO;
import com.milankas.training.dtos.user.UserOutputDTO;

import java.util.List;
import java.util.UUID;

public interface UserServiceInterface {


    List<UserOutputDTO> findAllUsers();

    UserOutputDTO findUserById(UUID id);

    UserOutputDTO saveUser(PostUserInputDTO user);

    UserOutputDTO updateUserById(UUID id, PatchUserInputDTO userForUpdate);

    Boolean deleteUserById(UUID id);

}
