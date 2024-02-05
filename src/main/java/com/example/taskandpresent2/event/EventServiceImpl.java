package com.example.taskandpresent2.event;

import com.example.taskandpresent2.event.model.EventDto;
import com.example.taskandpresent2.exception.PurchaseNotFoundException;
import com.example.taskandpresent2.exception.UserNotFoundException;
import com.example.taskandpresent2.pageable.Pagination;
import com.example.taskandpresent2.purchase.PurchaseMapper;
import com.example.taskandpresent2.purchase.model.PurchaseDto;
import com.example.taskandpresent2.user.UserMapper;
import com.example.taskandpresent2.user.UserService;
import com.example.taskandpresent2.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    @Lazy
    private UserService userService;

    @Override
    public EventDto getEventById(Long userId, Long id) {
        return EventMapper.toEventDto(eventRepository.findById(id).orElseThrow(()
                -> new PurchaseNotFoundException("Покупка не была создана.")));
    }

    @Override
    public List<EventDto> getAllEvents() {
        return eventRepository.findAll().stream().map(EventMapper::toEventDto).collect(toList());
    }

    @Override
    public List<EventDto> getAllEventsByParticipantsId(Long buyerId, Long participantsId, int from, int size) {
        return eventRepository.findAllByParticipantId(buyerId, Pagination.makePageRequest(from, size)).stream()
                .map(EventMapper::toEventDto)
                .sorted(Comparator.comparing(EventDto::getId))
                .collect(toList());
    }

    @Override
    public EventDto createEvent(EventDto eventDto) {
        validateEvent(eventDto);
        return EventMapper.toEventDto(eventRepository.save(EventMapper.toEvent(eventDto)));
    }

    @Transactional
    @Override
    public EventDto updateEvent(EventDto eventDto, Long id) {
        eventDto.setId(id);
        Event event = eventRepository.findById(eventDto.getId()).orElseThrow(()
                -> new UserNotFoundException("Пользователь не был зарегестрирован."));
        if (eventDto.getName() == null) {
            eventDto.setName(event.getName());
        }
        if (eventDto.getDescription() == null) {
            eventDto.setDescription(event.getDescription());
        }
        if (eventDto.getPurchases() == null) {
            eventDto.setPurchases(event.getPurchases());
        }
        if (eventDto.getParticipants() == null) {
            eventDto.setParticipants(event.getParticipants());
        }
        if (eventDto.getStatus() == null) {
            eventDto.setStatus(event.getStatus());
        }
        return EventMapper.toEventDto(eventRepository.save(EventMapper.toEvent(eventDto)));
    }

    @Transactional
    @Override
    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getAllParticipantsByEventId(Long userId, Long id, int from, int size) {
        return eventRepository.findAllUserByEventId(id, Pagination.makePageRequest(from, size)).stream()
                .map(UserMapper::toUserDto).collect(toList());
    }

    @Override
    public List<PurchaseDto> getAllPurchaseByEventId(Long userId, Long id, int from, int size) {
        return eventRepository.findAllPurchasesByEventId(id, Pagination.makePageRequest(from, size)).stream()
                .map(PurchaseMapper::toPurchaseDto).collect(toList());
    }

    @Override
    public List<UserDto> addUserToEvent(EventDto eventDto, Long id, int from, int size) {
        eventDto.getParticipants().add(UserMapper.toUser(userService.getUserById(id)));
        eventRepository.save(EventMapper.toEvent(eventDto));
        return eventRepository.findAllUserByEventId(eventDto.getId(), Pagination.makePageRequest(from, size)).stream()
                .map(UserMapper::toUserDto).collect(toList());
    }

    private void validateEvent(EventDto eventDto) {
        if (eventDto.getName().isEmpty() || eventDto.getName().equals(" ")) {
            throw new ValidationException("Введено пустое имя");
        }
    }
}
