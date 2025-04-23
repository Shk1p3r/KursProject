package labs.KP.entity;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "student", schema = "public")
@Entity
public class Student {
    @Id
    @SequenceGenerator(name = "student_seq", sequenceName = "student_id_student_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="student_seq")
    @Column(name = "id_student")
    private int id;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "fio")
    private String fio;
    @Column(name = "phone")
    private String phone;
    @Column(name = "group_number")
    private String groupNumber;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Lesson> lessons= new ArrayList<>();
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Exam> exams= new ArrayList<>();
}
