package com.milankas.training.Mappers.Password;

import com.milankas.training.dtos.password.PasswordDTO;
import com.milankas.training.persistance.entities.PasswordEntity;
import org.mapstruct.factory.Mappers;

public interface PasswordMapperInterface {

    PasswordMapperInterface MAPPER = Mappers.getMapper(PasswordMapperInterface.class);

    PasswordDTO EntityToDto(PasswordEntity passwordEntity);

    PasswordEntity DtoToEntity(PasswordDTO passwordDTO);

}
