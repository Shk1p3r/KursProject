package labs.KP.pojo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import labs.KP.entity.Instructor;
import labs.KP.entity.Lesson;
import labs.KP.repository.CarRepository;
import labs.KP.repository.InstructorRepository;
import labs.KP.repository.StudentRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorPojo {
private int id;
    private String fio;
    private int seniority;
    private List<LessonPojo> lessons = new ArrayList<>();
    private String category;
    @Autowired
    CarRepository carRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    InstructorRepository instructorRepository;
    public static InstructorPojo fromEntity(Instructor instructor) {
        if (instructor == null) {
            return null;
        }
        InstructorPojo pojo = new InstructorPojo();
        pojo.setId(instructor.getId());
        pojo.setFio(instructor.getFio());
        pojo.setSeniority(instructor.getSeniority());
        pojo.setCategory(instructor.getCategory() != null ? instructor.getCategory().getName() : null);

        List<LessonPojo> lessonPojos = new ArrayList<>();
        for (Lesson lesson : instructor.getLessons()) {
            lessonPojos.add(LessonPojo.fromEntity(lesson));
        }
        pojo.setLessons(lessonPojos);
        return pojo;
    }

    public static Instructor toEntity(InstructorPojo pojo) {
        if (pojo == null) {
            return null;
        }
        Instructor instructor = new Instructor();
        instructor.setFio(pojo.getFio());
        instructor.setSeniority(pojo.getSeniority());
        return instructor; // Категория и уроки обрабатываются в сервисе
    }
}
