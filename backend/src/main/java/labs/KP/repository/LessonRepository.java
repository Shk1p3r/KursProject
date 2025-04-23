package labs.KP.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import labs.KP.entity.Lesson;
@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer>{
    List<Lesson> findByCarId(Integer carId);
    @Query("""
    SELECT t FROM Lesson t
    WHERE t.startDate >= :startDate AND t.endDate <= :endDate
    """)
    List<Lesson> findByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
