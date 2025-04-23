package labs.KP.pojo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import labs.KP.entity.Exam;
import labs.KP.entity.Lesson;
import labs.KP.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentPojo {
    private int id;
    private Date dateOfBirth;
    private String fio;
    private String phone;
    private String groupNumber;
    private List<LessonPojo> lessons = new ArrayList<>();
    private List<ExamPojo> exams = new ArrayList<>();

    public static StudentPojo fromEntity(Student student) {
        if (student == null) {
            return null;
        }
        StudentPojo pojo = new StudentPojo();
        pojo.setId(student.getId());
        pojo.setDateOfBirth(student.getDateOfBirth());
        pojo.setFio(student.getFio());
        pojo.setPhone(student.getPhone());
        pojo.setGroupNumber(student.getGroupNumber());
        List<LessonPojo> lessons = new ArrayList<>();
        for (Lesson lesson : student.getLessons()) {
            lessons.add(LessonPojo.fromEntity(lesson));
        }
        pojo.setLessons(lessons);
        List<ExamPojo> exams = new ArrayList<>();
        for (Exam exam : student.getExams()) {
            exams.add(ExamPojo.fromEntity(exam));
        }
        pojo.setExams(exams);
        return pojo;
    }

    public static Student toEntity(StudentPojo pojo) {
        if (pojo == null) {
            return null;
        }
        Student student = new Student();
        student.setId(pojo.getId());
        student.setDateOfBirth(pojo.getDateOfBirth());
        student.setFio(pojo.getFio());
        student.setPhone(pojo.getPhone());
        student.setGroupNumber(pojo.getGroupNumber());
        
        student.setLessons(new ArrayList<>());
        student.setExams(new ArrayList<>());
    
        return student;
    }
}
