package com.example.taskandpresent2.user;


import com.example.taskandpresent2.Create;
import com.example.taskandpresent2.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

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
    @JoinTable(name = "event_participants",joinColumns = @JoinColumn(name = "participant_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "event_id",referencedColumnName = "id"))
    private List<Event> events = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "friends_id", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "friend_id")
    private Set<Long> friendsIds = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "friends_id", joinColumns = @JoinColumn(name = "user_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "user_id")),
            @AttributeOverride(name = "id", column = @Column(name = "friend_id"))
    })
    @AssociationOverrides({
            @AssociationOverride(name = "user_id", joinColumns = @JoinColumn(name = "user_id")),
            @AssociationOverride(name = "friend_id", joinColumns = @JoinColumn(name = "friend_id"))
    })
    @MapKeyColumn(name = "friend_id")
    @Column(name = "status")
    private Map<Long, Boolean> friendshipStatuses;

    public User(Long id, String name, String email) {
    }

    public User(Long id, String name, String email, Set<Long> friendsIds, Map<Long, Boolean> friendshipStatuses) {
    }
}

