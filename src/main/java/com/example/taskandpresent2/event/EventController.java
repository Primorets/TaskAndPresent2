package com.example.taskandpresent2.event;

import com.example.taskandpresent2.Create;
import com.example.taskandpresent2.Update;
import com.example.taskandpresent2.purchase.model.PurchaseDto;
import com.example.taskandpresent2.user.UserDto;
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
        return eventService.getAllEventsByParticipantsId(userId, id, from, size);
    }

    @GetMapping("/participants/{id}")
    public List<UserDto> getAllParticipantsByEventId(@RequestHeader(PARTICIPANTS) Long userId,
                                                     @PathVariable(value = "id") Long id,
                                                     @RequestParam(required = false, defaultValue = "0") int from,
                                                     @RequestParam(required = false, defaultValue = "20") int size) {
        log.info("Получен запрос на получение всех пользователей.");
        return eventService.getAllParticipantsByEventId(userId, id, from, size);
    }

    @GetMapping("/event_purchases/{id}")
    public List<PurchaseDto> getAllPurchaseByEventId(@RequestHeader(PARTICIPANTS) Long userId, @PathVariable Long id,
                                                     @RequestParam(required = false, defaultValue = "0") int from,
                                                     @RequestParam(required = false, defaultValue = "20") int size) {
        log.info("Получен запрос на получение всех покуп");
        return eventService.getAllPurchaseByEventId(userId, id, from, size);
    }

    @ResponseBody
    @PostMapping
    public EventDto createEvent(@RequestBody @Validated(Create.class) EventDto EventDto,
                                @RequestHeader(PARTICIPANTS) Long adminId) {
        log.info("Добавлен пользователь: " + EventDto);
        return eventService.createEvent(EventDto, adminId);
    }
/*
    @ResponseBody
    @PatchMapping("/image/{eventId}/{userId}")
    public EventDto addImage(@RequestHeader(PARTICIPANTS) Long userId,
                             @PathVariable Long eventId,
                             @RequestParam("image") MultipartFile image) throws IOException {
        log.info("Получен запрос от пользователя с ID: " + userId
                + "на добавление фотографии в мероприятие с ID: " + eventId);
        return eventService.saveImage(eventId, userId, image);
    }
*/
    @ResponseBody
    @PatchMapping("/{id}")
    public EventDto updateEvent(@RequestBody @Validated(Update.class) EventDto user, @PathVariable Long id) {
        log.info("Получен запрос на изменение данных о мероприятии с ID: " + id);
        return eventService.updateEvent(user, id);
    }

    @ResponseBody
    @PatchMapping("/adduser/{eventId}/{id}")
    public List<UserDto> addUserToEvent(@RequestHeader(PARTICIPANTS) Long adminId,
                                        @PathVariable Long eventId,
                                        @PathVariable Long id,
                                        @RequestParam(required = false, defaultValue = "0") int from,
                                        @RequestParam(required = false, defaultValue = "20") int size) {
        log.info("Получен запрос от пользователя с ID: " + adminId + " на добавление пользователя с ID: " + id
                + "в мероприятие с ID: " + eventId);
        return eventService.addUserToEvent(adminId, eventId, id, from, size);
    }

    @DeleteMapping("/{id}")
    public void deleteEventById(@RequestHeader(PARTICIPANTS) Long adminId, @PathVariable Long id) {
        //Добавить возможность удаления только по согласованию со всеми участниками группы
        log.info("Получен запрос на удаление пользователя с ID: " + id);
        eventService.deleteEventById(adminId, id);
    }
}
