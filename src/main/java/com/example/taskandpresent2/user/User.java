package com.example.taskandpresent2.user;


import com.example.taskandpresent2.Create;
import com.example.taskandpresent2.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank(groups = {Create.class})
    @Column(name = "name")
    private String name;
    @Email
    @NotBlank(groups = {Create.class})
    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "event_participants",
            joinColumns = @JoinColumn(name = "participant_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "event_id",referencedColumnName = "id"))
    private List<Event> events = new ArrayList<>();

}

