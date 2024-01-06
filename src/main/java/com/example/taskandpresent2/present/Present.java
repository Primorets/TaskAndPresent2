package com.example.taskandpresent2.present;

/*
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "present", schema = "public")*/
public class Present {/*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "presenter_id", referencedColumnName = "id")
    private User presenter;

    @Column(name = "is_consumable")
    private boolean IsConsumable;
*/
}
