package com.milankas.training.mapper.User;

import com.milankas.training.dto.user.PatchUserInputDTO;
import com.milankas.training.dto.user.PostUserInputDTO;
import com.milankas.training.dto.user.UserOutputDTO;
import com.milankas.training.persistance.entities.UserEntity;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapperInterface {

    UserMapperInterface MAPPER = Mappers.getMapper(UserMapperInterface.class);

    UserOutputDTO EntityToDto(UserEntity userEntity);

    UserEntity PostDtoToEntity(PostUserInputDTO userDTO);

    @BeforeMapping
    default void validateFields(@MappingTarget UserEntity userEntity, PatchUserInputDTO userDTO) {
        userDTO.setId(userEntity.getId());
        if (userDTO.getFirstName() == null) userDTO.setFirstName(userEntity.getFirstName());
        if (userDTO.getLastName() == null) userDTO.setLastName(userEntity.getLastName());
        if (userDTO.getEmail() == null) userDTO.setEmail(userEntity.getEmail());
    }
    UserEntity PatchDtoToEntity(@MappingTarget UserEntity userEntity, PatchUserInputDTO userDTO);

}
