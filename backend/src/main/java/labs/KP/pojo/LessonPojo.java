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
    private Integer carId;
    private Integer studentId;
    private Integer instructorId;
   public static LessonPojo fromEntity(Lesson lesson) {
        LessonPojo pojo = new LessonPojo();
        pojo.setId(lesson.getId());
        pojo.setStartDate(lesson.getStartDate());
        pojo.setEndDate(lesson.getEndDate());
        if (lesson.getCar() != null) pojo.setCarId(lesson.getCar().getId());
        if (lesson.getStudent() != null) pojo.setStudentId(lesson.getStudent().getId());
        if (lesson.getInstructor() != null) pojo.setInstructorId(lesson.getInstructor().getId());
        return pojo;
    }

    public static Lesson toEntity(LessonPojo pojo) {
        Lesson lesson = new Lesson();
        lesson.setId(pojo.getId());
        lesson.setStartDate(pojo.getStartDate());
        lesson.setEndDate(pojo.getEndDate());
        return lesson;
    }
}
