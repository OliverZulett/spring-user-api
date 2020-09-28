package com.milankas.training.mapper.Password;

import com.milankas.training.dto.password.PasswordDTO;
import com.milankas.training.persistance.entities.PasswordEntity;

public class PasswordMapper implements PasswordMapperInterface {

    @Override
    public PasswordDTO EntityToDto(PasswordEntity passwordEntity) {
        return PasswordMapperInterface.MAPPER.EntityToDto(passwordEntity);
    }

    @Override
    public PasswordEntity DtoToEntity(PasswordDTO passwordDTO) {
        return PasswordMapperInterface.MAPPER.DtoToEntity(passwordDTO);
    }

}
