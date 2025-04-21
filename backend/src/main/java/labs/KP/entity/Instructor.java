package labs.KP.entity;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="instructor", schema="public")
@Entity
public class Instructor {
    @Id
    @SequenceGenerator(name = "instructor_seq", sequenceName = "instructor_id_instructor_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="instructor_seq")
    @Column(name = "id_instructor")
    private int id;
    @Column(name = "fio") 
    private String fio;
    @Column(name = "seniority") 
    private int seniority;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "name_category", referencedColumnName = "name_category")
    private Category category;
    @OneToMany(mappedBy = "instructor", fetch = FetchType.EAGER)
    private List<Lesson> lessons;
}
