package labs.KP.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import labs.KP.entity.Category;
import labs.KP.entity.Instructor;
import labs.KP.entity.Lesson;
import labs.KP.pojo.InstructorPojo;
import labs.KP.pojo.LessonPojo;
import labs.KP.repository.CarRepository;
import labs.KP.repository.CategoryRepository;
import labs.KP.repository.InstructorRepository;
import labs.KP.repository.LessonRepository;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Transactional
    public InstructorPojo create(InstructorPojo pojo) {
        Instructor instructor = new Instructor();
        instructor.setFio(pojo.getFio());
        instructor.setSeniority(pojo.getSeniority());

        // Устанавливаем категорию
        if (pojo.getCategory() != null) {
            Category category = categoryRepository.findById(pojo.getCategory()).orElse(null);
            instructor.setCategory(category);
        }

        // Привязываем существующие уроки
        List<Lesson> lessons = new ArrayList<>();
        if (pojo.getLessons() != null && !pojo.getLessons().isEmpty()) {
            for (LessonPojo lessonPojo : pojo.getLessons()) {
                Lesson lesson = lessonRepository.findById(lessonPojo.getId()).orElse(null);
                if (lesson != null) {
                    lesson.setInstructor(instructor);
                    lessons.add(lesson);
                }
            }
        }
        instructor.setLessons(lessons);

        Instructor saved = instructorRepository.save(instructor);
        return InstructorPojo.fromEntity(saved);
    }

    public InstructorPojo findById(Integer id) {
        Instructor instructor = instructorRepository.findById(id).orElse(null);
        return InstructorPojo.fromEntity(instructor);
    }

    public List<InstructorPojo> findAll() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<InstructorPojo> pojos = new ArrayList<>();
        for (Instructor instructor : instructors) {
            pojos.add(InstructorPojo.fromEntity(instructor));
        }
        return pojos;
    }

    @Transactional
    public InstructorPojo update(InstructorPojo pojo) {
        Instructor existing = instructorRepository.findById(pojo.getId()).orElse(null);
        if (existing == null) {
            return null;
        }

        existing.setFio(pojo.getFio() != null ? pojo.getFio() : existing.getFio());
        existing.setSeniority(pojo.getSeniority() != 0 ? pojo.getSeniority() : existing.getSeniority());

        // Очистка существующих уроков
        for (Lesson lesson : existing.getLessons()) {
            lesson.setInstructor(null);
        }
        existing.getLessons().clear();

        // Привязка новых уроков
        if (pojo.getLessons() != null && !pojo.getLessons().isEmpty()) {
            List<Lesson> lessons = new ArrayList<>();
            for (LessonPojo lessonPojo : pojo.getLessons()) {
                Lesson lesson = lessonRepository.findById(lessonPojo.getId()).orElse(null);
                if (lesson != null) {
                    lesson.setInstructor(existing);
                    // Обновляем поля урока из LessonPojo
                    lesson.setStartDate(lessonPojo.getStartDate());
                    lesson.setEndDate(lessonPojo.getEndDate());
                    if (lessonPojo.getCarId() != null && lessonPojo.getCarId() != 0) {
                        lesson.setCar(carRepository.findById(lessonPojo.getCarId()).orElse(null));
                    } else {
                        lesson.setCar(null);
                    }
                    lessons.add(lesson);
                }
            }
            existing.setLessons(lessons);
        }

        Instructor updated = instructorRepository.save(existing);
        return InstructorPojo.fromEntity(updated);
    }
    
    @Transactional
    public void deleteById(Integer id) {
        instructorRepository.deleteById(id);
    }

    public List<InstructorPojo> search(String fio, Integer seniority) {
        List<Instructor> instructors = instructorRepository.searchInstructors(fio, seniority);
        List<InstructorPojo> pojos = new ArrayList<>();
        for (Instructor instructor : instructors) {
            pojos.add(InstructorPojo.fromEntity(instructor));
        }
        return pojos;
    }
}
