package sk.stuba.fei.vsa.jpa4;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Titul titul;

    @OneToMany
    @JoinColumn(name = "prednasajuci_id")
    private List<Subject> prednasky;

    @ManyToMany
    @JoinColumn(name = "cvicenie_id")
    private List<Subject> cvicenia;
}
