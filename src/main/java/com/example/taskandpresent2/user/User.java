package com.example.taskandpresent2.user;


import com.example.taskandpresent2.Create;
import com.example.taskandpresent2.event.model.Event;
import com.example.taskandpresent2.purchase.Purchase;
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
@Table(name = "USERS", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @NotBlank(groups = {Create.class})
    @Column(name = "NAME")
    private String name;
    @Email
    @NotBlank(groups = {Create.class})
    @Column(name = "EMAIL")
    private String email;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "EVENT_PARTICIPANTS",
            joinColumns = {@JoinColumn(name = "PARTICIPANT_ID",
                    referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "EVENT_ID", referencedColumnName = "ID")})
    private List<Event> events = new ArrayList<>();

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_PURCHASES", joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PURCHASE_ID", referencedColumnName = "ID")})
    private List<Purchase> purchases = new ArrayList<>();
}

