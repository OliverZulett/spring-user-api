package com.milankas.training.services.user;

import com.milankas.training.Mappers.User.UserMapperInterface;
import com.milankas.training.dtos.user.PatchUserInputDTO;
import com.milankas.training.dtos.user.PostUserInputDTO;
import com.milankas.training.dtos.user.UserOutputDTO;
import com.milankas.training.persistance.entities.UserEntity;
import com.milankas.training.persistance.repositories.UserRepository;
import com.milankas.training.services.password.PasswordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapperInterface userMapper;
    @Autowired
    PasswordServiceInterface passwordService;


    @Override
    public List<UserOutputDTO> findAllUsers() {
        List<UserEntity> usersReceived = userRepository.findAll();
        List<UserOutputDTO> usersToSend = new ArrayList();
        usersReceived.forEach(user -> {
            usersToSend.add(userMapper.EntityToDto(user));
        });
        return usersToSend;
    }

    @Override
    public UserOutputDTO findUserById(UUID id) {
        return userMapper.EntityToDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserOutputDTO saveUser(@Valid PostUserInputDTO user) {
        UserEntity userToSave = userMapper.PostDtoToEntity(user);
        userToSave.setPasswords(passwordService.generatePasswordRegister(user.getPassword()));
        return userMapper.EntityToDto(userRepository.save(userToSave));
    }

    @Override
    public UserOutputDTO updateUserById(UUID id, @Valid PatchUserInputDTO user) {
        UserEntity userFound = userRepository.findById(id).orElse(null);
        if (userFound == null) return null;
        UserEntity userToUpdate = userMapper.PatchDtoToEntity(userFound, user);
        return userMapper.EntityToDto(userRepository.save(userToUpdate));
    }

    @Override
    public Boolean deleteUserById(UUID id) {
        UserEntity userFound = userRepository.findById(id).orElse( null);
        if (userFound == null) return null;
        userRepository.delete(userFound);
        return true;
    }

}
