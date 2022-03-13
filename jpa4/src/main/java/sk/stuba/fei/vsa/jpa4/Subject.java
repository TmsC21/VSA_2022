package sk.stuba.fei.vsa.jpa4;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nazov;
    private String rocnik;
    private int kredity;

    @ManyToOne
    @JoinColumn(name="prednasajuci_id", nullable=false)
    private Person prednasajuci;
}
