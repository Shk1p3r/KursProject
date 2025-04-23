package labs.KP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import labs.KP.entity.Instructor;
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>{
    @Query("""
            SELECT i FROM Instructor i
            WHERE
                (:fio IS NULL OR UPPER(i.fio) LIKE CONCAT('%', UPPER(CAST(:fio AS string)), '%')) AND
                (:seniority IS NULL OR i.seniority = :seniority)
    """)
    List<Instructor> searchInstructors(@Param("fio") String fio,@Param("seniority") Integer seniority);
}
