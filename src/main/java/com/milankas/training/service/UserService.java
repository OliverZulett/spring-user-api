package com.milankas.training.service;

import com.milankas.training.exception.PasswordExistingException;
import com.milankas.training.exception.ResourceNotFoundException;
import com.milankas.training.mapper.UserMapper;
import com.milankas.training.dto.user.PatchUserInputDTO;
import com.milankas.training.dto.user.PostUserInputDTO;
import com.milankas.training.dto.user.UserOutputDTO;
import com.milankas.training.persistance.entity.PasswordEntity;
import com.milankas.training.persistance.entity.UserEntity;
import com.milankas.training.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordService passwordService;

    public List<UserOutputDTO> findAllUsers() {
        List<UserEntity> usersReceived = userRepository.findAll();
        List<UserOutputDTO> usersToSend = new ArrayList();
        usersReceived.forEach(user -> {
            usersToSend.add(userMapper.EntityToDto(user));
        });
        return usersToSend;
    }

    public UserOutputDTO findUserById(UUID id) {
        return userMapper.EntityToDto(userRepository.findById(id).orElse(null));
    }

    public UserOutputDTO saveUser(@Valid PostUserInputDTO user) {
        UserEntity userToSave = userMapper.PostDtoToEntity(user);
        userToSave.setPasswords(passwordService.generatePasswordRegister(user.getPassword()));
        return userMapper.EntityToDto(userRepository.save(userToSave));
    }

    public UserOutputDTO updateUserById(UUID id, @Valid PatchUserInputDTO user) throws PasswordExistingException {
        UserEntity userFound = userRepository.findById(id).orElse(null);
        if (userFound == null) return null;
        UserEntity userToUpdate = userMapper.PatchDtoToEntity(userFound, user);
        userToUpdate.setPasswords(passwordService.updatePasswordRegister(userToUpdate.getPasswords(), user.getPassword()));
        return userMapper.EntityToDto(userRepository.save(userToUpdate));
    }

    public Boolean deleteUserById(UUID id) {
        UserEntity userFound = userRepository.findById(id).orElse( null);
        if (userFound == null) return null;
        userRepository.delete(userFound);
        return true;
    }

}
