package sda.hibernate.theory.relationship.onetoone;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Individual {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Unique identity", referencedColumnName = "id")
    private CNP cnp;
}
