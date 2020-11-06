package com.milankas.training.mapper;

import com.milankas.training.dto.user.PatchUserInputDTO;
import com.milankas.training.dto.user.PostUserInputDTO;
import com.milankas.training.dto.user.UserOutputDTO;
import com.milankas.training.persistance.entity.UserEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mappings({@Mapping(target = "userId", source = "id")})
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
