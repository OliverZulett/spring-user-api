package com.milankas.training.Mappers.User;

import com.milankas.training.dtos.user.PatchUserInputDTO;
import com.milankas.training.dtos.user.PostUserInputDTO;
import com.milankas.training.dtos.user.UserOutputDTO;
import com.milankas.training.persistance.entities.UserEntity;

public class UserMapper implements UserMapperInterface {

    @Override
    public UserOutputDTO EntityToDto(UserEntity userEntity) {
        return UserMapperInterface.MAPPER.EntityToDto(userEntity);
    }

    @Override
    public UserEntity PostDtoToEntity(PostUserInputDTO userDTO) {
        return UserMapperInterface.MAPPER.PostDtoToEntity(userDTO);
    }

    @Override
    public UserEntity PatchDtoToEntity(UserEntity userEntity, PatchUserInputDTO userDTO) {
        return UserMapperInterface.MAPPER.PatchDtoToEntity(userEntity, userDTO);
    }

}
