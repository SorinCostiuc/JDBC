package sda.hibernate.theory.embedded;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
    private String city;
    private String country;
    private Integer number;
}
