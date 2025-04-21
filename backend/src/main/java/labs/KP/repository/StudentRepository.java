package labs.KP.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import labs.KP.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    List<Student> findByDateOfBirth(Date dateOfBirth);
    List<Student> findByFio(String fio);
    List<Student> findByPhone(String phone);
    List<Student> findByGroupNumber(String groupNumber);
}
