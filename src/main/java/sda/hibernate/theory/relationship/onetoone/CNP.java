package sda.hibernate.theory.relationship.onetoone;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CNP {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String value;
}
