package labs.KP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import labs.KP.entity.Category;
import labs.KP.entity.Instructor;
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>{
    List<Instructor> findByFioContainingIgnoreCase(String fio);
    List<Instructor> findBySeniority(Integer seniority);
    List<Instructor> findByCategory(Category category);
}
