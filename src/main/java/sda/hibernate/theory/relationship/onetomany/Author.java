package sda.hibernate.theory.relationship.onetomany;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    private String Name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author") // AuthorToList<Book>
    List<Book> books;
}
