package com.example.taskandpresent2.event;

//import com.example.taskandpresent2.event.dto.ImageDto;

import com.example.taskandpresent2.event.model.Event;
import com.example.taskandpresent2.exception.EventNotFoundException;
import com.example.taskandpresent2.exception.UserNotFoundException;
import com.example.taskandpresent2.pageable.Pagination;
import com.example.taskandpresent2.purchase.PurchaseMapper;
import com.example.taskandpresent2.purchase.model.PurchaseDto;
import com.example.taskandpresent2.user.UserDto;
import com.example.taskandpresent2.user.UserMapper;
import com.example.taskandpresent2.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;
/*
    @Autowired
    private ImageRepository imageRepository;
*/

    @Autowired
    private EventMapper eventMapper;



    @Override
    public EventDto getEventById(Long userId, Long id) {
        checkUserInEvent(userId,id);
        //проверка на вхождение пользователя в мероприятие
        return EventMapper.toEventDto(eventRepository.findById(id).orElseThrow(()
                -> new EventNotFoundException("Покупка не была создана.")));
    }

    @Override
    public EventDto getEventByIdForPurchase(Long id) {
        return EventMapper.toEventDto(eventRepository.findById(id).orElseThrow(()
                -> new EventNotFoundException("Покупка не была создана.")));
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


    @Transactional
    @Override
    public EventDto createEvent(EventDto eventDto, Long adminId) {
        validateEvent(eventDto);
        if (eventDto.getStatus() == null) {
            eventDto.setStatus(StatusEvent.NEW);
        }
        if (eventDto.getParticipants() == null) {
            List<Long> admin = new ArrayList<>();
            admin.add(adminId);
            eventDto.setParticipants(admin);
        }
        if (eventDto.getPurchases() == null) {
            eventDto.setPurchases(new ArrayList<>());
        }
       /* EventDto finalEvent = EventMapper.toEventDto(eventRepository.save(EventMapper.toEvent(eventDto)));
        finalEvent.getParticipants().add(adminId);
        eventRepository.save(EventMapper.toEvent(finalEvent));
        return getEventById(adminId, finalEvent.getId());*/
        return EventMapper.toEventDto(eventRepository.save(EventMapper.toEvent(eventDto)));
    }

    @Transactional
    @Override
    public EventDto updateEvent(EventDto eventDto, Long id) {
        eventDto.setId(id);
        Event event = eventRepository.findById(eventDto.getId()).orElseThrow(()
                -> new EventNotFoundException("Пользователь не был зарегестрирован."));
        if (eventDto.getName() == null) {
            eventDto.setName(event.getName());
        }
        if (eventDto.getDescription() == null) {
            eventDto.setDescription(event.getDescription());
        }
        if (eventDto.getStatus() == null) {
            eventDto.setStatus(event.getStatus());
        }
        return EventMapper.toEventDto(eventRepository.save(EventMapper.toEvent(eventDto)));
    }

    @Transactional
    @Override
    public void deleteEventById(Long userId,Long id) {
        checkUserInEvent(userId,id);
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
                .map(PurchaseMapper::toPurchaseDto)
                .collect(toList());
    }

    @Transactional
    @Override
    public List<UserDto> addUserToEvent(Long adminId, Long eventId, Long id, int from, int size) {
        checkUserInEvent(adminId,eventId);
        EventDto eventDto = getEventById(adminId,eventId);
        eventDto.getParticipants().add(id);
        eventRepository.save(EventMapper.toEvent(eventDto));
        return eventRepository.findAllUserByEventId(eventDto.getId(), Pagination.makePageRequest(from, size)).stream()
                .map(UserMapper::toUserDto)
                .collect(toList());
    }

    @Override
    public void checkUserInEvent(Long userId, Long eventId) {
        if (eventRepository.findAllUsersIdByEventId(userId, eventId) == null) {
            throw new UserNotFoundException("User id: " + userId + "не учавствует в мероприятии id:" + eventId);
        }
    }
/*
    @Override
    public EventDto saveImage(Long eventId, Long userId, MultipartFile image) throws IOException {
        EventDto eventDto = getEventById(userId, eventId);
        ImageDto imageDto = ImageMapper.toImageEntity(image);
        eventDto.getImages().add(imageDto);
        return EventMapper.INSTANCE.toEventDto(eventRepository.save(EventMapper.INSTANCE.toEvent(eventDto)));
    }*/

    private void validateEvent(EventDto eventDto) {
        if (eventDto.getName().isEmpty() || eventDto.getName().equals(" ")) {
            throw new ValidationException("Введено пустое имя");
        }
    }
}
