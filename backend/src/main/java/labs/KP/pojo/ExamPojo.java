package labs.KP.pojo;
import java.sql.Date;

import labs.KP.entity.Exam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamPojo {
    private int id;
    private String typeOfExams;
    private String result;
    private Date date;
    private StudentPojo student;
    public static ExamPojo fromEntity(Exam exam) {
        if (exam == null) {
            return null;
        }
        ExamPojo pojo = new ExamPojo();
        pojo.setId(exam.getId());
        pojo.setTypeOfExams(exam.getTypeOfExams());
        pojo.setResult(exam.getResult());
        pojo.setDate(exam.getDate());
        pojo.setStudent(StudentPojo.fromEntity(exam.getStudent()));
        return pojo;
    }
    public static Exam toEntity(ExamPojo pojo) {
        if (pojo == null) {
            return null;
        }
        Exam exam = new Exam();
        exam.setId(pojo.getId());
        exam.setTypeOfExams(pojo.getTypeOfExams());
        exam.setResult(pojo.getResult());
        exam.setDate(pojo.getDate());
        exam.setStudent(StudentPojo.toEntity(pojo.getStudent()));
        return exam;
    }
}
