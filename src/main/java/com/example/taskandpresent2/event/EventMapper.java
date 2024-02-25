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
                eventDto.getStart(),
                eventDto.getEnd(),
                eventDto.getParticipants(),
                eventDto.getPurchases());
    }
    public static EventDto toEventDto(Event event){
        return new EventDto(event.getId(),
                event.getName(),
                event.getDescription(),
                event.getStatus(),
                event.getStart(),
                event.getEnd(),
                event.getParticipants(),
                event.getPurchases());
    }
}
