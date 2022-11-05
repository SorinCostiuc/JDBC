package sda.hibernate.theory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee { // will not be generated in this order
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 32, nullable = false)
    private String name;

    private double salary;
    private String position;


}
