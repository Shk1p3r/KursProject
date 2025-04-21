package labs.KP.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import labs.KP.entity.Car;
@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
    List<Car> findByMark(String mark);
    List<Car> findByModel(String model);
    List<Car> findByYearOfProduction(Integer yearOfProduction);
    Optional<Car> findByLicensePlateNumber(String licensePlateNumber);
}
