package labs.KP.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import labs.KP.entity.Exam;
@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer>{
    @Query("""
            SELECT e FROM Exam e
            WHERE
                (COALESCE(:date, e.date) = e.date) AND
                (:typeOfExams IS NULL OR UPPER(e.typeOfExams) = UPPER(CAST(:typeOfExams AS string))) AND
                (:result IS NULL OR UPPER(e.result) = UPPER(CAST(:result AS string)))
    """)
    List<Exam> searchExams(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,@Param("typeOfExams") String typeOfExams,@Param("result") String result);
}
