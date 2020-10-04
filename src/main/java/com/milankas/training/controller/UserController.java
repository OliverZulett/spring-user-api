package com.milankas.training.controller;

import com.milankas.training.dto.user.PatchUserInputDTO;
import com.milankas.training.dto.user.PostUserInputDTO;
import com.milankas.training.dto.user.UserOutputDTO;
import com.milankas.training.exception.ResourceNotFoundException;
import com.milankas.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserOutputDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserOutputDTO> getUserById(@Valid @PathVariable(value = "id") UUID userId) throws ResourceNotFoundException {
        UserOutputDTO userFound = userService.findUserById(userId);
        if (userFound == null) throw new ResourceNotFoundException("User not found for id: " + userId);
        return ResponseEntity.ok(userFound);
    }

    @PostMapping("/users")
    public ResponseEntity<UserOutputDTO> createUser(@Valid @RequestBody PostUserInputDTO userToSave) {
        return new ResponseEntity<>(userService.saveUser(userToSave), HttpStatus.CREATED);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserOutputDTO> updateUser(@Valid @PathVariable(value = "id") UUID userId, @Valid @RequestBody PatchUserInputDTO userForUpdate) throws ResourceNotFoundException {
        UserOutputDTO userUpdated = userService.updateUserById(userId, userForUpdate);
        if (userUpdated == null) throw new ResourceNotFoundException("User not found for id: " + userId);
        return new ResponseEntity<UserOutputDTO>(userUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") UUID userId) throws ResourceNotFoundException {
        Boolean userDeleted = userService.deleteUserById(userId);
        if (userDeleted == null) throw new ResourceNotFoundException("User not found for id: " + userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
