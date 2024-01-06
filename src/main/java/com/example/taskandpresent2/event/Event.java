package com.example.taskandpresent2.event;

import com.example.taskandpresent2.purchase.Purchase;
import com.example.taskandpresent2.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events", schema = "public")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;//Уникальный идентификационный номер задачи, по которому её можно будет найти.
    @Column(name = "name")
    private String name;//Название, кратко описывающее суть задачи (например, «Переезд»).
    @Column(name = "description")
    private String description;//Описание, в котором раскрываются детали.

    @Enumerated(EnumType.STRING)
    @Column(name = "status_event")
    private StatusEvent status;//Статус, отображающий её прогресс.

    @ManyToOne()
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private User participant;//пользователи, которые учавствуют в мероприятии

    @ManyToOne()
    @JoinColumn(name = "purchase_id", referencedColumnName = "id")
    private Purchase purchase; //класс шаблон

    @Column(name = "start_date")
    private LocalDateTime start;

    @Column(name = "end_date")
    private LocalDateTime end;
}
