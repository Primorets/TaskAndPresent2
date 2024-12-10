package com.example.taskandpresent2.event.model;

import com.example.taskandpresent2.event.StatusEvent;
import com.example.taskandpresent2.purchase.Purchase;
import com.example.taskandpresent2.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EVENTS", schema = "public")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;//Уникальный идентификационный номер задачи, по которому её можно будет найти.
    @Column(name = "NAME")
    private String name;//Название, кратко описывающее суть задачи (например, «Переезд»).
    @Column(name = "DESCRIPTION")
    private String description;//Описание, в котором раскрываются детали.

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_EVENT")
    private StatusEvent status;//Статус, отображающий её прогресс.

    @Column(name = "START_DATE")
    private LocalDateTime start;

    @Column(name = "END_DATE")
    private LocalDateTime end;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "EVENT_PARTICIPANTS", joinColumns = {@JoinColumn(name = "EVENT_ID",
            referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PARTICIPANT_ID", referencedColumnName = "ID")})


    private List<User> participants;//пользователи, которые учавствуют в мероприятии

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "EVENT_PURCHASES",
            joinColumns = {@JoinColumn(name = "EVENT_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PURCHASE_ID",referencedColumnName = "ID")})
    private List<Purchase> purchases;

  /*  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event")
    private List<Image> images;*/

}
