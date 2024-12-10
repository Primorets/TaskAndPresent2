package com.example.taskandpresent2.event.dto;

import com.example.taskandpresent2.event.StatusEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ShortEventDto {
    private Long id;//Уникальный идентификационный номер задачи, по которому её можно будет найти.
    private String name;//Название, кратко описывающее суть задачи (например, «Переезд»).
    private String description;//Описание, в котором раскрываются детали.
    private StatusEvent status;//Статус, отображающий её прогресс.
    private LocalDateTime start;
    private LocalDateTime end;
}
