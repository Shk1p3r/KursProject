package labs.KP.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "exam", schema = "public")
@Entity
public class Exam {
    @Id
    @SequenceGenerator(name = "exam_seq", sequenceName = "exam_id_exam_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="exam_seq")
    @Column(name = "id_exam")
    private int id;
    @Column(name = "type_of_exams")
    private String typeOfExams;
    @Column(name = "result")
    private String result;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_student", referencedColumnName = "id_student")
    private Student student;
}
