package com.example.taskandpresent2.event;

import com.example.taskandpresent2.event.model.Event;
import com.example.taskandpresent2.purchase.Purchase;
import com.example.taskandpresent2.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    public static Event toEvent(EventDto eventDto) {
        Event event = new Event();
        event.setId(eventDto.getId());
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        event.setStatus(eventDto.getStatus());
        event.setStart(eventDto.getStart());
        event.setEnd(eventDto.getEnd());
        List<User> users = eventDto.getParticipants().stream().map(userId -> {
            User user = new User();
            user.setId(userId);
            return user;
        }).collect(Collectors.toList());
        event.setParticipants(users);
        List<Purchase> purchases = eventDto.getPurchases().stream().map(purchaseId -> {
            Purchase purchase = new Purchase();
            purchase.setId(purchaseId);
            return purchase;
        }).collect(Collectors.toList());
        event.setPurchases(purchases);
        return event;
    }

    public static EventDto toEventDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setName(event.getName());
        eventDto.setDescription(event.getDescription());
        eventDto.setStatus(event.getStatus());
        eventDto.setStart(event.getStart());
        eventDto.setEnd(event.getEnd());
        List<Long> userIds = event.getParticipants().stream()
                .map(User::getId)
                .collect(Collectors.toList());
        eventDto.setParticipants(userIds);
        List<Long> purchaseIds = event.getPurchases().stream()
                .map(Purchase::getId)
                .collect(Collectors.toList());
        eventDto.setParticipants(purchaseIds);
        return eventDto;
    }





    /*
        public static EventDto toFullEventDto(Event event) {
            return new EventDto(event.getId(),
                    event.getName(),
                    event.getDescription(),
                    event.getStatus(),
                    event.getStart(),
                    event.getEnd(),
                    event.getParticipants() != null ? event.getParticipants().stream()
                            .map(UserMapper::toFullUserDto)
                            .collect(Collectors.toList()) : new ArrayList<>(),
                    event.getPurchases() != null ? event.getPurchases().stream()
                            .map(PurchaseMapper::toPurchaseDto)
                            .collect(Collectors.toList()) : new ArrayList<>(),
                    event.getImages() != null ? event.getImages().stream()
                            .map(ImageMapper::toImageDto)
                            .collect(Collectors.toList()) : new ArrayList<>());
        }

    public static EventDto toFullEventDto(Event event) {
        return new EventDto(event.getId(),
                event.getName(),
                event.getDescription(),
                event.getStatus(),
                event.getStart(),
                event.getEnd(),
                event.getParticipants() != null ? event.getParticipants().stream()
                        .map(UserMapper::toShortUserDto)
                        .collect(Collectors.toList()) : new ArrayList<>(),
                event.getPurchases() != null ? event.getPurchases().stream()
                        .map(PurchaseMapper::toPurchaseDto)
                        .collect(Collectors.toList()) : new ArrayList<>(),
                event.getImages() != null ? event.getImages().stream()
                        .map(ImageMapper::toImageDto)
                        .collect(Collectors.toList()) : new ArrayList<>());
    }


    public static ShortEventDto toShortEventDto(Event event) {
        return new ShortEventDto(event.getId(),
                event.getName(),
                event.getDescription(),
                event.getStatus(),
                event.getStart(),
                event.getEnd());
    }

 */

}
