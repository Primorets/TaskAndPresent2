package com.example.taskandpresent2.purchase;

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
@Table(name = "purchases", schema = "public")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private StatusPurchases statusPurchases;

    @Column(name = "is_consumable")
    private boolean IsConsumable;

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private User buyer;

    @Enumerated(EnumType.STRING)
    private Dimension dimension;
    @Column(name = "quantity")
    private int quantity;

}
