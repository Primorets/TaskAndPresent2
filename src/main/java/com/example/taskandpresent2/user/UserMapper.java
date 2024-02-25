package com.example.taskandpresent2.user;

import com.example.taskandpresent2.event.EventMapper;
import com.example.taskandpresent2.user.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getEvents().stream()
                        .map(EventMapper::toEventDto)
                        .collect(Collectors.toList()));
    }

    public static User toUser(UserDto userDto) {
        return new User(userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getEventDtoList().stream()
                        .map(EventMapper::toEvent)
                        .collect(Collectors.toList()));
    }

}
