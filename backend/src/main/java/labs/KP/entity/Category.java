package labs.KP.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Category {
    @Id
    @Column(name = "name_category")
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Instructor> instructors;
}
