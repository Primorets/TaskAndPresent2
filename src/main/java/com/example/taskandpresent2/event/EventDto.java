package com.example.taskandpresent2.event;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private Long id;//Уникальный идентификационный номер задачи, по которому её можно будет найти.
    private String name;//Название, кратко описывающее суть задачи (например, «Переезд»).
    private String description;//Описание, в котором раскрываются детали.
    private StatusEvent status;//Статус, отображающий её прогресс.
    private LocalDateTime start;
    private LocalDateTime end;

    @JsonIgnore
    private List<Long> participants;//пользователи, которые учавствуют в мероприятии
    @JsonIgnore
    private List<Long> purchases; //класс шаблон
   // private List<ImageDto> images;
}
