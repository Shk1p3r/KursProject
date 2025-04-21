package labs.KP.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import labs.KP.entity.Car;
import labs.KP.pojo.CarPojo;
import labs.KP.repository.CarRepository;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public CarPojo create(CarPojo pojo) {
        List<CarPojo> findCarPojo = search(null,null,null,pojo.getLicensePlateNumber());
        if(!findCarPojo.isEmpty())
        {
            return null;
        }
        Car car = CarPojo.toEntity(pojo);
        Car saved = carRepository.save(car);
        return CarPojo.fromEntity(saved);
    }

    public CarPojo findById(Integer id) {
        Optional<Car> car = carRepository.findById(id);
        return CarPojo.fromEntity(car.orElse(null));
    }

    public List<CarPojo> findAll() {
        List<Car> cars = carRepository.findAll();
        List<CarPojo> pojos = new ArrayList<>();
        for (Car car : cars) {
            pojos.add(CarPojo.fromEntity(car));
        }
        return pojos;
    }

    public CarPojo update(CarPojo pojo) {
        Car car = CarPojo.toEntity(pojo);
        Car updated = carRepository.save(car);
        return CarPojo.fromEntity(updated);
    }
    @Transactional
    public void deleteById(Integer id) {
        carRepository.deleteById(id);
    }
    public List<CarPojo> search(String mark, String model, Integer year, String plate) {
        List<Car> cars = carRepository.searchCars(mark, model, year, plate);
        List<CarPojo> pojos = new ArrayList<>();
        for (Car car : cars) {
            pojos.add(CarPojo.fromEntity(car));
        }
        return pojos;
    }
}
