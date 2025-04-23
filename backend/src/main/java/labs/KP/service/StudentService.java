package labs.KP.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import labs.KP.entity.Exam;
import labs.KP.entity.Lesson;
import labs.KP.entity.Student;
import labs.KP.pojo.ExamPojo;
import labs.KP.pojo.LessonPojo;
import labs.KP.pojo.StudentPojo;
import labs.KP.repository.ExamRepository;
import labs.KP.repository.LessonRepository;
import labs.KP.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ExamRepository examRepository;

    public StudentPojo create(StudentPojo pojo) {
        Student student = StudentPojo.toEntity(pojo);
        for (LessonPojo lessonPojo : pojo.getLessons()) {
            Lesson lesson = lessonRepository.findById(lessonPojo.getId()).orElse(null);
            if (lesson != null) {
                lesson.setStudent(student);
                student.getLessons().add(lesson);
            }
        }
        for (ExamPojo examPojo : pojo.getExams()) {
            Exam exam = examRepository.findById(examPojo.getId()).orElse(null);
            if (exam != null) {
                exam.setStudent(student);
                student.getExams().add(exam);
            }
        }
        Student saved = studentRepository.save(student);
        return StudentPojo.fromEntity(saved);
    }

    public StudentPojo findById(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        return StudentPojo.fromEntity(student.orElse(null));
    }

    public List<StudentPojo> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentPojo> pojos = new ArrayList<>();
        for (Student student : students) {
            pojos.add(StudentPojo.fromEntity(student));
        }
        return pojos;
    }

    public StudentPojo update(StudentPojo pojo) {
        Optional<Student> optional = studentRepository.findById(pojo.getId());
        if (optional.isEmpty()) {
            return null;
        }
    
        Student existing = optional.get();
        existing.setFio(pojo.getFio());
        existing.setDateOfBirth(pojo.getDateOfBirth());
        existing.setPhone(pojo.getPhone());
        existing.setGroupNumber(pojo.getGroupNumber());
        List<Integer> updatedLessonIds = new ArrayList<>();
        for (LessonPojo lp : pojo.getLessons()) {
            updatedLessonIds.add(lp.getId());
        }
        for (Lesson lesson : new ArrayList<>(existing.getLessons())) {
            if (!updatedLessonIds.contains(lesson.getId())) {
                lesson.setStudent(null);
                lessonRepository.save(lesson);
            }
        }
    
        existing.getLessons().clear();
        for (LessonPojo lessonPojo : pojo.getLessons()) {
            Lesson lesson = lessonRepository.findById(lessonPojo.getId()).orElse(null);
            if (lesson != null) {
                lesson.setStudent(existing);
                existing.getLessons().add(lesson);
            }
        }
        List<Integer> updatedExamIds = new ArrayList<>();
        for (ExamPojo ep : pojo.getExams()) {
            updatedExamIds.add(ep.getId());
        }
        for (Exam exam : new ArrayList<>(existing.getExams())) {
            if (!updatedExamIds.contains(exam.getId())) {
                exam.setStudent(null);
                examRepository.save(exam);
            }
        }
    
        existing.getExams().clear();
        for (ExamPojo examPojo : pojo.getExams()) {
            Exam exam = examRepository.findById(examPojo.getId()).orElse(null);
            if (exam != null) {
                exam.setStudent(existing);
                existing.getExams().add(exam);
            }
        }
    
        Student updated = studentRepository.save(existing);
        return StudentPojo.fromEntity(updated);
    }

    @Transactional
    public void deleteById(Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            for (Exam exam : student.getExams()) {
                exam.setStudent(null);
                examRepository.save(exam);
            }
            for (Lesson lesson : student.getLessons()) {
                lesson.setStudent(null);
                lessonRepository.save(lesson);
            }
            studentRepository.delete(student);
        }
    }

    public List<StudentPojo> search(Date dateOfBirth, String fio, String phone, String groupNumber) {
        List<Student> students = studentRepository.searchStudents(dateOfBirth, fio, phone, groupNumber);
        List<StudentPojo> pojos = new ArrayList<>();
        for (Student student : students) {
            pojos.add(StudentPojo.fromEntity(student));
        }
        return pojos;
    }

}
