package labs.KP.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import labs.KP.entity.Student;
import labs.KP.pojo.StudentPojo;
import labs.KP.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public StudentPojo create(StudentPojo pojo) {
        Student student = StudentPojo.toEntity(pojo);
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
        Student student = StudentPojo.toEntity(pojo);
        Student updated = studentRepository.save(student);
        return StudentPojo.fromEntity(updated);
    }
    @Transactional
    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }
    public List<StudentPojo> findByDateOfBirth(Date dateOfBirth) {
        List<Student> students = studentRepository.findByDateOfBirth(dateOfBirth);
        List<StudentPojo> pojos = new ArrayList<>();
        for (Student student : students) {
            pojos.add(StudentPojo.fromEntity(student));
        }
        return pojos;
    }
    public List<StudentPojo> findByFio(String fio) {
        List<Student> students = studentRepository.findByFioContainingIgnoreCase(fio);
        List<StudentPojo> pojos = new ArrayList<>();
        for (Student student : students) {
            pojos.add(StudentPojo.fromEntity(student));
        }
        return pojos;
    }
    public List<StudentPojo> findByPhone(String phone) {
        List<Student> students = studentRepository.findByPhoneContainingIgnoreCase(phone);
        List<StudentPojo> pojos = new ArrayList<>();
        for (Student student : students) {
            pojos.add(StudentPojo.fromEntity(student));
        }
        return pojos;
    }
    public List<StudentPojo> findByGroupNumber(String groupNumber) {
        List<Student> students = studentRepository.findByGroupNumberContainingIgnoreCase(groupNumber);
        List<StudentPojo> pojos = new ArrayList<>();
        for (Student student : students) {
            pojos.add(StudentPojo.fromEntity(student));
        }
        return pojos;
    }
}
