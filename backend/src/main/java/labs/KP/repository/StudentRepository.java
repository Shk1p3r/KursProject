package labs.KP.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import labs.KP.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    @Query("""
            SELECT s FROM Student s
            WHERE
                (COALESCE(:dateOfBirth, s.dateOfBirth) = s.dateOfBirth) AND
                (:fio IS NULL OR UPPER(s.fio) LIKE CONCAT('%', UPPER(CAST(:fio AS string)), '%')) AND
                (:phone IS NULL OR UPPER(s.phone) LIKE CONCAT('%', UPPER(CAST(:phone AS string)), '%')) AND
                (:groupNumber IS NULL OR UPPER(s.groupNumber) LIKE CONCAT('%', UPPER(CAST(:groupNumber AS string)), '%'))
    """)
    List<Student> searchStudents(@Param("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOfBirth,@Param("fio") String fio,@Param("phone") String phone,@Param("groupNumber") String groupNumber);
}
