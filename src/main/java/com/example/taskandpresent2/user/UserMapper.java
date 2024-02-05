package com.example.taskandpresent2.user;

import com.example.taskandpresent2.user.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getFriendsIds(),
                user.getFriendshipStatuses());
    }

    public static User toUser(UserDto userDto) {
        return new User(userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getFriendsIds(),
                userDto.getFriendshipStatuses());
    }
}
