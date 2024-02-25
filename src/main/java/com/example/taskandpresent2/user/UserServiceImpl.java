package com.example.taskandpresent2.user;

import com.example.taskandpresent2.exception.DuplicateEmailException;
import com.example.taskandpresent2.exception.UserNotFoundException;
import com.example.taskandpresent2.exception.ValidationException;
import com.example.taskandpresent2.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getUserById(Long id) {
        return UserMapper.toUserDto(userRepository.findById(id).orElseThrow(()
                -> new UserNotFoundException("Пользователь не был зарегестрирован.")));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toUserDto).collect(toList());
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        userDto.setId(id);
        User user1 = userRepository.findById(userDto.getId()).orElseThrow(()
                -> new UserNotFoundException("Пользователь не был зарегестрирован."));
        if (userDto.getEmail() == null) {
            userDto.setEmail(user1.getEmail());
        }
        if (userDto.getName() == null) {
            userDto.setName(user1.getName());
        }
        if (userRepository.findById(id).stream()
                .filter(user2 -> !Objects.equals(user2.getId(), userDto.getId()))
                .anyMatch(user2 -> user2.getEmail()
                        .equals(userDto.getEmail()))) {
            throw new DuplicateEmailException("Email уже зарегестрирован");

        }
        if (userDto.getEventDtoList() == null) {
            userDto.setEventDtoList(new ArrayList<>());
        }
        return UserMapper.toUserDto(userRepository.save(UserMapper.toUser(userDto)));
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        validateUser(userDto);
        try {
            if (userDto.getEventDtoList() == null) {
                userDto.setEventDtoList(new ArrayList<>());
            }
            return UserMapper.toUserDto(userRepository.save(UserMapper.toUser(userDto)));
        } catch (DuplicateEmailException duplicateEmailException) {
            throw new DuplicateEmailException("Email уже зарегестрирован");
        }
    }

    @Override
    public List<UserDto> searchUserByName(String text, int from, int size) {
        return userRepository.findAll().stream()
                .filter(user -> user.getName().contains(text))
                .map(UserMapper::toUserDto)
                .collect(toList());
    }

    private void validateUser(UserDto user) {
        if (!user.getEmail().contains("@") || user.getEmail() == null) {
            throw new ValidationException("Введён не правильный email");
        }
        if (user.getName().isEmpty() || user.getName().contains(" ")) {
            throw new ValidationException("Введено пустое имя");
        }
    }
}
