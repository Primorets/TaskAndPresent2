package com.example.taskandpresent2.event;

import com.example.taskandpresent2.event.model.EventDto;
import com.example.taskandpresent2.user.User;

import java.util.List;

public interface EventService {
    EventDto getEventById(Long id);

    List<EventDto> getAllEvents();

    List<User> getAllUsersByIvents();

    EventDto createEvent(EventDto eventDto);

    EventDto updateEvent(EventDto user, Long id);

    void deleteEventById(Long id);
}
