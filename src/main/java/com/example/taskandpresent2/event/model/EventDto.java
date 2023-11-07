package com.example.taskandpresent2.event.model;

import com.example.taskandpresent2.event.StatusEvent;
import com.example.taskandpresent2.purchase.Purchase;
import com.example.taskandpresent2.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {
    private int id;//Уникальный идентификационный номер задачи, по которому её можно будет найти.
    private String name;//Название, кратко описывающее суть задачи (например, «Переезд»).
    private String description;//Описание, в котором раскрываются детали.
    private StatusEvent status;//Статус, отображающий её прогресс.
    private User participants;//пользователи, которые учавствуют в мероприятии
    private Purchase purchases; //класс шаблон
    private LocalDateTime start;
    private LocalDateTime end;
}
