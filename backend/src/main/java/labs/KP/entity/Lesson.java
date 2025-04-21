package labs.KP.entity;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "lesson", schema = "public")
@Entity
public class Lesson {
    @Id
    @SequenceGenerator(name = "lesson_seq", sequenceName = "lesson_id_lesson_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="lesson_seq")
    @Column(name = "id_lesson")
    private int id;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_student", referencedColumnName = "id_student")
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_instructor", referencedColumnName = "id_instructor")
    private Instructor instructor;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car", referencedColumnName = "id_car")
    private Car car;
}
