package com.milankas.training.controller;

import com.milankas.training.dto.user.PatchUserInputDTO;
import com.milankas.training.dto.user.PostUserInputDTO;
import com.milankas.training.dto.user.UserOutputDTO;
import com.milankas.training.exception.PasswordExistingException;
import com.milankas.training.exception.ResourceNotFoundException;
import com.milankas.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;

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

    @DeleteMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteUser(@Valid @PathVariable(value = "id") UUID userId) throws ResourceNotFoundException {
        Boolean userDeleted = userService.deleteUserById(userId);
        if (userDeleted == null) throw new ResourceNotFoundException("User not found for id: " + userId);
    }

}
