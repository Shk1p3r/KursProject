package labs.KP.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import labs.KP.entity.Exam;
import labs.KP.entity.Student;
@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer>{
    List<Exam> findByDate(Date date);
    List<Exam> findByTypeOfExamsContainingIgnoreCase(String typeOfExams);
    List<Exam> findByResultContainingIgnoreCase(String result);
    List<Exam> findByStudent(Student student);
}
