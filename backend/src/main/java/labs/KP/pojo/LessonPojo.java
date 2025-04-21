package labs.KP.pojo;

import java.sql.Date;

import labs.KP.entity.Lesson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonPojo {
    private int id;
    private Date startDate;
    private Date endDate;
    private StudentPojo student;
    private InstructorPojo instructor;
    private CarPojo car;
    public static LessonPojo fromEntity(Lesson lesson) {
        if (lesson == null) {
            return null;
        }
        LessonPojo pojo = new LessonPojo();
        pojo.setId(lesson.getId());
        pojo.setStartDate(lesson.getStartDate());
        pojo.setEndDate(lesson.getEndDate());
        pojo.setStudent(StudentPojo.fromEntity(lesson.getStudent()));
        pojo.setInstructor(InstructorPojo.fromEntity(lesson.getInstructor()));
        pojo.setCar(CarPojo.fromEntity(lesson.getCar()));
        return pojo;
    }
    public static Lesson toEntity(LessonPojo pojo) {
        if (pojo == null) {
            return null;
        }
        Lesson lesson = new Lesson();
        lesson.setId(pojo.getId());
        lesson.setStartDate(pojo.getStartDate());
        lesson.setEndDate(pojo.getEndDate());
        lesson.setStudent(StudentPojo.toEntity(pojo.getStudent()));
        lesson.setInstructor(InstructorPojo.toEntity(pojo.getInstructor()));
        lesson.setCar(CarPojo.toEntity(pojo.getCar()));
        return lesson;
    }
}
