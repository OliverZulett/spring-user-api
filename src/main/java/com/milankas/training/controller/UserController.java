package com.milankas.training.controller;

import com.milankas.training.dto.password.PasswordDTO;
import com.milankas.training.dto.user.PatchUserInputDTO;
import com.milankas.training.dto.user.PostUserInputDTO;
import com.milankas.training.dto.user.UserOutputDTO;
import com.milankas.training.exception.InvalidParamException;
import com.milankas.training.exception.PasswordExistingException;
import com.milankas.training.exception.ResourceNotFoundException;
import com.milankas.training.persistance.entity.UserEntity;
import com.milankas.training.service.EncryptionService;
import com.milankas.training.service.PasswordService;
import com.milankas.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private PasswordService passwordService;

    @GetMapping("/users")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<UserOutputDTO> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public UserOutputDTO getUserById(@Valid @PathVariable(value = "id") UUID userId) throws ResourceNotFoundException {
        UserOutputDTO userFound = userService.findUserById(userId);
        if (userFound == null) throw new ResourceNotFoundException("User not found for id: " + userId);
        return userFound;
    }

    @PostMapping("/users")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public UserOutputDTO createUser(@Valid @RequestBody PostUserInputDTO userToSave) {
        return userService.saveUser(userToSave);
    }

    @PatchMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public UserOutputDTO updateUser(@Valid @PathVariable(value = "id") UUID userId, @Valid @RequestBody PatchUserInputDTO userForUpdate) throws ResourceNotFoundException, PasswordExistingException {
        UserOutputDTO userUpdated = userService.updateUserById(userId, userForUpdate);
        if (userUpdated == null) throw new ResourceNotFoundException("User not found for id: " + userId);
        return userUpdated;
    }

    @PatchMapping("/users/{id}/change-password")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void updatePassword(@Valid @PathVariable(value = "id") UUID userId, @Valid @RequestBody PasswordDTO passwords) throws ResourceNotFoundException, PasswordExistingException, InvalidParamException {
        Optional<UserEntity> userFound = this.userService.findUSerEntityById(userId);
        if (!userFound.isPresent()) throw new ResourceNotFoundException("User not found for id: " + userId);
        if (!this.encryptionService.verifyPassword(this.passwordService.getCurrentPasswordByUserId(userId), passwords.getOldPassword()))
            throw new PasswordExistingException("Incorrect actual password");
        if (!this.userService.updatePassword(userFound.get(), passwords.getNewPassword()))
            throw new InternalError("Update password fail");
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteUser(@Valid @PathVariable(value = "id") UUID userId) throws ResourceNotFoundException {
        Boolean userDeleted = userService.deleteUserById(userId);
        if (userDeleted == null) throw new ResourceNotFoundException("User not found for id: " + userId);
    }

}
