package com.example.taskandpresent2.event;

import com.example.taskandpresent2.Create;
import com.example.taskandpresent2.Update;
import com.example.taskandpresent2.event.model.EventDto;
import com.example.taskandpresent2.user.User;
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

    private static final String OWNER = "X-Sharer-User-Id";
    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public EventDto getUserById(@PathVariable Long id) {
        log.info("Получен запрос на получение пользователя по ID: " + id);
        return eventService.getEventById(id);
    }

    @GetMapping
    public List<EventDto> getAllUsers() {
        log.info("Получен запрос на получение всех пользователей.");
        return eventService.getAllEvents();
    }

    @GetMapping("/participants/{id}")
    public List<User> getAllParticipantsByEvent(@PathVariable String id) {
        log.info("Получен запрос на получение всех пользователей.");
        return eventService.getAllUsersByIvents();
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

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        log.info("Получен запрос на удаление пользователя с ID: " + id);
        eventService.deleteEventById(id);
    }
}
