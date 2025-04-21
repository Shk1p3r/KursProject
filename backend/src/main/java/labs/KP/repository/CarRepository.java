package labs.KP.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import labs.KP.entity.Car;
@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
    @Query("""
        SELECT c FROM Car c
        WHERE
            (:mark IS NULL OR UPPER(c.mark) LIKE CONCAT('%', UPPER(:mark), '%')) AND
            (:model IS NULL OR UPPER(c.model) LIKE CONCAT('%', UPPER(:model), '%')) AND
            (:plate IS NULL OR UPPER(c.licensePlateNumber) LIKE CONCAT('%', UPPER(:plate), '%')) AND
            (:year IS NULL OR c.yearOfProduction = :year)
    """)
    List<Car> searchCars(@Param("mark") String mark,@Param("model") String model,@Param("year") Integer year,@Param("plate") String plate);
}
