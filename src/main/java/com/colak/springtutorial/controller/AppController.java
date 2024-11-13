package com.colak.springtutorial.controller;

import com.colak.springtutorial.config.ClientCredentials;
import com.colak.springtutorial.dto.GenericResponse;
import com.colak.springtutorial.dto.UserDto;
import com.colak.springtutorial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class AppController {

    private final UserService userService;
    private final ClientCredentials clientCredentials;

    // http://localhost:8080/user
    @GetMapping("/user")
    public ResponseEntity<GenericResponse> getUsers() {
        GenericResponse genericResponse = new GenericResponse(true, "Users returned successfully", userService.findAll(), HttpStatus.OK.value(), LocalDateTime.now());
        return ResponseEntity.ok(genericResponse);
    }

    @PostMapping("/user")
    public ResponseEntity<GenericResponse> addUser(@RequestBody UserDto userDto) {
        GenericResponse genericResponse = new GenericResponse(true, "User saved successfully", userService.add(userDto), HttpStatus.CREATED.value(), LocalDateTime.now());
        return ResponseEntity.ok(genericResponse);
    }


    // http://localhost:8080/clientCredentials
    @GetMapping("/clientCredentials")
    public ResponseEntity<GenericResponse> getClientCredentials() {
        GenericResponse genericResponse = new GenericResponse(true, "Users returned successfully", clientCredentials, HttpStatus.OK.value(), LocalDateTime.now());
        return ResponseEntity.ok(genericResponse);
    }
}
