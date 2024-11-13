package com.colak.springtutorial.service;


import com.colak.springtutorial.dto.UserDto;
import com.colak.springtutorial.jpa.User;
import com.colak.springtutorial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto add(UserDto userDto) {
        User user = new User(0, userDto.getUsername(), userDto.getPassword());
        User saved = userRepository.save(user);
        userDto.setId(saved.getId());
        return userDto;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .parallelStream()
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getPassword()))
                .toList();
    }
}
