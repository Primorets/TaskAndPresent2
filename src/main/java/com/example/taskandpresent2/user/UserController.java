package com.example.taskandpresent2.user;


import com.example.taskandpresent2.Create;
import com.example.taskandpresent2.Update;
import com.example.taskandpresent2.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        log.info("Получен запрос на получение пользователя по ID: " + id);
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("Получен запрос на получение всех пользователей.");
        return userService.getAllUsers();
    }

    @ResponseBody
    @PostMapping
    public UserDto createUser(@RequestBody @Validated(Create.class) UserDto userDto) {
        log.info("Добавлен пользователь: " + userDto);
        return userService.createUser(userDto);
    }

    @ResponseBody
    @PatchMapping("/{id}")
    public UserDto updateUser(@RequestBody @Validated(Update.class) UserDto user, @PathVariable Long id) {
        log.info("Получен запрос на изменение данных о пользователе с ID: " + id);
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        log.info("Получен запрос на удаление пользователя с ID: " + id);
        userService.deleteUserById(id);
    }
}
