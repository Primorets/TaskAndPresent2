package com.example.taskandpresent2.event;

import com.example.taskandpresent2.Create;
import com.example.taskandpresent2.Update;
import com.example.taskandpresent2.event.model.EventDto;
import com.example.taskandpresent2.purchase.model.PurchaseDto;
import com.example.taskandpresent2.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/event")
public class EventController {

    private static final String PARTICIPANTS = "X-Sharer-User-Id";
    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public EventDto getEventById(@RequestHeader(PARTICIPANTS) Long userId, @PathVariable Long id) {
        log.info("Получен запрос на получение мероприятия по ID: " + id);
        return eventService.getEventById(userId, id);
    }

    @GetMapping
    public List<EventDto> getAllEvents() {
        log.info("Получен запрос на получение всех пользователей.");
        return eventService.getAllEvents();
    }

    @GetMapping("/events/{id}")
    public List<EventDto> getAllEventsByParticipantsId(@RequestHeader(PARTICIPANTS) Long userId, @PathVariable Long id,
                                                   @RequestParam(required = false, defaultValue = "0") int from,
                                                   @RequestParam(required = false, defaultValue = "20") int size) {
        log.info("Получен запрос на получение всех пользователей.");
        return eventService.getAllEventsByParticipantsId(userId, id,from,size);
    }

    @GetMapping("/participants/{id}")
    public List<UserDto> getAllParticipantsByEventId(@RequestHeader(PARTICIPANTS) Long userId,
                                                     @PathVariable(value = "id") Long id,
                                                     @RequestParam(required = false, defaultValue = "0") int from,
                                                     @RequestParam(required = false, defaultValue = "20") int size) {
        log.info("Получен запрос на получение всех пользователей.");
        return eventService.getAllParticipantsByEventId(userId,id,from,size);
    }

    @GetMapping("/event_purchases/{id}")
    public List<PurchaseDto> getAllPurchaseByEventId(@RequestHeader(PARTICIPANTS) Long userId, @PathVariable Long id,
                                                     @RequestParam(required = false, defaultValue = "0") int from,
                                                     @RequestParam(required = false, defaultValue = "20") int size){
        log.info("Получен запрос на получение всех покуп");
        return eventService.getAllPurchaseByEventId(userId,id,from,size);
    }

    @ResponseBody
    @PostMapping
    public EventDto createUser(@RequestBody @Validated(Create.class) EventDto EventDto) {
        log.info("Добавлен пользователь: " + EventDto);
        return eventService.createEvent(EventDto);
    }

    @ResponseBody
    @PatchMapping("/{id}")
    public EventDto updateUser(@RequestBody @Validated(Update.class) EventDto user, @PathVariable Long id) {
        log.info("Получен запрос на изменение данных о пользователе с ID: " + id);
        return eventService.updateEvent(user, id);
    }
    @ResponseBody
    @PatchMapping("/add_user/{id}")
    public List<UserDto> addUserToEvent (@RequestBody @Validated(Update.class) EventDto eventDto,
                                         @PathVariable Long id,
                                         @RequestParam(required = false, defaultValue = "0") int from,
                                         @RequestParam(required = false, defaultValue = "20") int size){
        return eventService.addUserToEvent(eventDto,id,from,size);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        log.info("Получен запрос на удаление пользователя с ID: " + id);
        eventService.deleteEventById(id);
    }


}
