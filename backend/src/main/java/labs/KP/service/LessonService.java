package labs.KP.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import labs.KP.entity.Car;
import labs.KP.entity.Instructor;
import labs.KP.entity.Lesson;
import labs.KP.entity.Student;
import labs.KP.pojo.LessonPojo;
import labs.KP.repository.CarRepository;
import labs.KP.repository.InstructorRepository;
import labs.KP.repository.LessonRepository;
import labs.KP.repository.StudentRepository;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    @Transactional
    public LessonPojo create(LessonPojo pojo) {
        Lesson lesson = new Lesson();
        lesson.setStartDate(pojo.getStartDate());
        lesson.setEndDate(pojo.getEndDate());

        if (pojo.getCarId() != null && pojo.getCarId() != 0) {
            Car car = carRepository.findById(pojo.getCarId()).orElse(null);
            lesson.setCar(car);
        }

        if (pojo.getStudentId() != null && pojo.getStudentId() != 0) {
            Student student = studentRepository.findById(pojo.getStudentId()).orElse(null);
            lesson.setStudent(student);
        }

        if (pojo.getInstructorId() != null && pojo.getInstructorId() != 0) {
            Instructor instructor = instructorRepository.findById(pojo.getInstructorId()).orElse(null);
            lesson.setInstructor(instructor);
        }

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

    @Transactional
    public LessonPojo update(LessonPojo pojo) {
        Lesson lesson = lessonRepository.findById(pojo.getId())
            .orElse(null);
        if (lesson == null) {
            return null;
        }

        lesson.setStartDate(pojo.getStartDate());
        lesson.setEndDate(pojo.getEndDate());

        if (pojo.getCarId() != null && pojo.getCarId() != 0) {
            Car car = carRepository.findById(pojo.getCarId()).orElse(null);
            lesson.setCar(car);
        } else {
            lesson.setCar(null);
        }

        if (pojo.getStudentId() != null && pojo.getStudentId() != 0) {
            Student student = studentRepository.findById(pojo.getStudentId()).orElse(null);
            lesson.setStudent(student);
        } else {
            lesson.setStudent(null);
        }

        if (pojo.getInstructorId() != null && pojo.getInstructorId() != 0) {
            Instructor instructor = instructorRepository.findById(pojo.getInstructorId()).orElse(null);
            lesson.setInstructor(instructor);
        } else {
            lesson.setInstructor(null);
        }

        Lesson updated = lessonRepository.save(lesson);
        return LessonPojo.fromEntity(updated);
    }

    @Transactional
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
}
