package labs.KP.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import labs.KP.entity.Car;
import labs.KP.pojo.CarPojo;
import labs.KP.repository.CarRepository;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public CarPojo create(CarPojo pojo) {
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

    public void deleteById(Integer id) {
        carRepository.deleteById(id);
    }

    public List<CarPojo> findByMark(String mark) {
        List<Car> cars = carRepository.findByMark(mark);
        List<CarPojo> pojos = new ArrayList<>();
        for (Car car : cars) {
            pojos.add(CarPojo.fromEntity(car));
        }
        return pojos;
    }

    public List<CarPojo> findByModel(String model) {
        List<Car> cars = carRepository.findByModel(model);
        List<CarPojo> pojos = new ArrayList<>();
        for (Car car : cars) {
            pojos.add(CarPojo.fromEntity(car));
        }
        return pojos;
    }

    public List<CarPojo> findByYearOfProduction(Integer yearOfProduction) {
        List<Car> cars = carRepository.findByYearOfProduction(yearOfProduction);
        List<CarPojo> pojos = new ArrayList<>();
        for (Car car : cars) {
            pojos.add(CarPojo.fromEntity(car));
        }
        return pojos;
    }

    public CarPojo findByLicensePlateNumber(String licensePlateNumber) {
        Optional<Car> car = carRepository.findByLicensePlateNumber(licensePlateNumber);
        return CarPojo.fromEntity(car.orElse(null));
    }
}
