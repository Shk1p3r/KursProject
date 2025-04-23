package labs.KP.entity;

import java.util.ArrayList;
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
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Instructor> instructors = new ArrayList<>();
}
