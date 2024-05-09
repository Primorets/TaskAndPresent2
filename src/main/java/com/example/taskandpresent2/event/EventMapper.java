package com.example.taskandpresent2.event;

import com.example.taskandpresent2.event.model.EventDto;
import com.example.taskandpresent2.purchase.PurchaseMapper;
import com.example.taskandpresent2.user.UserMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EventMapper {

    public static Event toEvent(EventDto eventDto){
        PurchaseMapper purchaseMapper= new PurchaseMapper();
        return new Event(eventDto.getId(),
                eventDto.getName(),
                eventDto.getDescription(),
                eventDto.getStatus(),
                eventDto.getStart(),
                eventDto.getEnd(),
                eventDto.getParticipants().stream().map(UserMapper::toUser).collect(Collectors.toList()),
                eventDto.getPurchases().stream().map(purchaseMapper::toPurchase).collect(Collectors.toList()));
    }
    public static EventDto toEventDto(Event event){
        return new EventDto(event.getId(),
                event.getName(),
                event.getDescription(),
                event.getStatus(),
                event.getStart(),
                event.getEnd(),
                event.getParticipants().stream().map(UserMapper::toUserDto).collect(Collectors.toList()),
                event.getPurchases().stream().map(PurchaseMapper::toPurchaseDto).collect(Collectors.toList()));
    }
}
