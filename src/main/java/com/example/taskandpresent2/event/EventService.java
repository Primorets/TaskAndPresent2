package com.example.taskandpresent2.event;

import com.example.taskandpresent2.event.model.EventDto;
import com.example.taskandpresent2.user.model.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventService {

    EventDto getEventById(Long userId, Long id);

    List<EventDto> getAllEvents();

    List<EventDto> getAllEventsByParticipantsId(Long buyerId,Long participantsId, int from, int size);

    EventDto createEvent(EventDto eventDto);

    @Transactional
    EventDto updateEvent(EventDto eventDto, Long id);

    @Transactional
    void deleteEventById(Long id);

    List<UserDto> getAllParticipantsByEventId(Long userId, Long id, int from, int size);
}
