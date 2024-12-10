package com.example.taskandpresent2.purchase;

import com.example.taskandpresent2.event.model.Event;
import com.example.taskandpresent2.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PURCHASES", schema = "public")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS_PURCHASES")
    @Enumerated(EnumType.STRING)
    private StatusPurchases statusPurchases;

    @Column(name = "IS_CONSUMABLE")
    private boolean IsConsumable;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "EVENT_PURCHASES", joinColumns = {@JoinColumn(name = "PURCHASE_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "EVENT_ID",referencedColumnName = "ID")})
    private Event event;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_PURCHASES", joinColumns = {@JoinColumn(name = "PURCHASE_ID", referencedColumnName = "ID")},
    inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")})
    private User buyer;

    @Column(name = "DIMENSION")
    @Enumerated(EnumType.STRING)
    private Dimension dimension;
    @Column(name = "QUANTITY")
    private int quantity;

}
