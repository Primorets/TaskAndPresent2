package com.example.taskandpresent2.user;

import com.example.taskandpresent2.user.model.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    void deleteUserById(Long id);

    UserDto updateUser(UserDto user, Long id);

    UserDto createUser(UserDto user);

    User getUserForBookingMapper(Long id);
}
