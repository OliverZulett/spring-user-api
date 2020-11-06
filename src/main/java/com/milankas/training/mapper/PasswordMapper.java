package com.milankas.training.mapper;

import com.milankas.training.dto.password.PasswordDTO;
import com.milankas.training.persistance.entity.PasswordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PasswordMapper {

    PasswordMapper MAPPER = Mappers.getMapper(PasswordMapper.class);

    PasswordDTO EntityToDto(PasswordEntity passwordEntity);

    PasswordEntity DtoToEntity(PasswordDTO passwordDTO);

}
