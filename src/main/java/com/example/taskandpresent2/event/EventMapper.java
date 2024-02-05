package com.example.taskandpresent2.event;

import com.example.taskandpresent2.event.model.EventDto;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public static Event toEvent(EventDto eventDto){
        return new Event(eventDto.getId(),
                eventDto.getName(),
                eventDto.getDescription(),
                eventDto.getStatus(),
                eventDto.getParticipants(),
                eventDto.getPurchases(),
                eventDto.getStart(),
                eventDto.getEnd());
    }
    public static EventDto toEventDto(Event event){
        return new EventDto(event.getId(),
                event.getName(),
                event.getDescription(),
                event.getStatus(),
                event.getParticipants(),
                event.getPurchases(),
                event.getStart(),
                event.getEnd());
    }
}
