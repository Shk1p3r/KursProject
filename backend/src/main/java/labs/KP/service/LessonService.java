package labs.KP.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import labs.KP.entity.Lesson;
import labs.KP.pojo.CarPojo;
import labs.KP.pojo.InstructorPojo;
import labs.KP.pojo.LessonPojo;
import labs.KP.pojo.StudentPojo;
import labs.KP.repository.LessonRepository;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public LessonPojo create(LessonPojo pojo) {
        Lesson lesson = LessonPojo.toEntity(pojo);
        Lesson saved = lessonRepository.save(lesson);
        return LessonPojo.fromEntity(saved);
    }

    public LessonPojo findById(Integer id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        return LessonPojo.fromEntity(lesson.orElse(null));
    }

    public List<LessonPojo> findAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        List<LessonPojo> pojos = new ArrayList<>();
        for (Lesson lesson : lessons) {
            pojos.add(LessonPojo.fromEntity(lesson));
        }
        return pojos;
    }

    public LessonPojo update(LessonPojo pojo) {
        Lesson lesson = LessonPojo.toEntity(pojo);
        Lesson updated = lessonRepository.save(lesson);
        return LessonPojo.fromEntity(updated);
    }

    public void deleteById(Integer id) {
        lessonRepository.deleteById(id);
    }

    public List<LessonPojo> findByDateRange(Date startDate, Date endDate) {
        List<Lesson> lessons = lessonRepository.findByDateRange(startDate, endDate);
        List<LessonPojo> pojos = new ArrayList<>();
        for (Lesson lesson : lessons) {
            pojos.add(LessonPojo.fromEntity(lesson));
        }
        return pojos;
    }

    public List<LessonPojo> findByStudent(StudentPojo studentPojo) {
        List<Lesson> lessons = lessonRepository.findByStudent(StudentPojo.toEntity(studentPojo));
        List<LessonPojo> pojos = new ArrayList<>();
        for (Lesson lesson : lessons) {
            pojos.add(LessonPojo.fromEntity(lesson));
        }
        return pojos;
    }

    public List<LessonPojo> findByInstructor(InstructorPojo instructorPojo) {
        List<Lesson> lessons = lessonRepository.findByInstructor(InstructorPojo.toEntity(instructorPojo));
        List<LessonPojo> pojos = new ArrayList<>();
        for (Lesson lesson : lessons) {
            pojos.add(LessonPojo.fromEntity(lesson));
        }
        return pojos;
    }

    public List<LessonPojo> findByCar(CarPojo carPojo) {
        List<Lesson> lessons = lessonRepository.findByCar(CarPojo.toEntity(carPojo));
        List<LessonPojo> pojos = new ArrayList<>();
        for (Lesson lesson : lessons) {
            pojos.add(LessonPojo.fromEntity(lesson));
        }
        return pojos;
    }
}
