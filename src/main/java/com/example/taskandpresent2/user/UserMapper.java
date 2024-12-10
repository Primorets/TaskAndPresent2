package com.example.taskandpresent2.user;

import com.example.taskandpresent2.event.model.Event;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
/*
    public static UserDto toFullUserDto(User user) {
        return new UserDto(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getEvents().stream()
                        .map(EventMapper::toShortEventDto)
                        .collect(Collectors.toList()));
    }

    public static ShortUserDto toShortUserDto(User user) {
        return new ShortUserDto(user.getId(),
                user.getName(),
                user.getEmail());
    }

    @Autowired
    public static EventService eventService;
    public static User toUserFromShort(ShortUserDto shortUserDto){
        return new User(shortUserDto.getId(),
                shortUserDto.getName(),
                shortUserDto.getEmail(),
                eventService.)
    }



    public static User toUser(UserDto userDto) {
        return new User(userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getEventDtoList().stream()
                        .map(eventDto -> EventMapper.toEvent(eventDto))
                        .collect(Collectors.toList()));
    }

    public static UserDto shortUserDtoToFullUserDto(ShortUserDto shortUserDto){
        return new UserDto(shortUserDto.getId(),
                shortUserDto.getName(),
                shortUserDto.getEmail(),
                new ArrayList<>() );
    }

    public static UserDto toShortUserDtoFromFullUserDto(ShortUserDto shortUserDto) {
        return new UserDto(shortUserDto.getId(),
                shortUserDto.getName(),
                shortUserDto.getEmail());
    }
*/
public static UserDto toUserDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setName(user.getName());
    userDto.setEmail(user.getEmail());
    // Преобразование списка идентификаторов событий
    List<Long> eventIds = user.getEvents().stream()
            .map(Event::getId)
            .collect(Collectors.toList());
    userDto.setEventIds(eventIds);
    return userDto;
}

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        // Преобразование списка идентификаторов событий
        List<Event> events = userDto.getEventIds().stream()
                .map(eventId -> {
                    Event event = new Event();
                    event.setId(eventId);
                    return event;
                })
                .collect(Collectors.toList());
        user.setEvents(events);
        return user;
    }



}
