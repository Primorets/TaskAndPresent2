package com.example.taskandpresent2.event.model;

import com.example.taskandpresent2.event.StatusEvent;
import com.example.taskandpresent2.purchase.Purchase;
import com.example.taskandpresent2.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class EventDto {
    private Long id;//Уникальный идентификационный номер задачи, по которому её можно будет найти.
    private String name;//Название, кратко описывающее суть задачи (например, «Переезд»).
    private String description;//Описание, в котором раскрываются детали.
    private StatusEvent status;//Статус, отображающий её прогресс.
    private LocalDateTime start;
    private LocalDateTime end;

    private List<User> participants;//пользователи, которые учавствуют в мероприятии
    private List<Purchase> purchases; //класс шаблон


}
