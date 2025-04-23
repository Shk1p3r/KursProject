package labs.KP.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import labs.KP.entity.Car;
import labs.KP.entity.Lesson;
import labs.KP.pojo.CarPojo;
import labs.KP.pojo.LessonPojo;
import labs.KP.repository.CarRepository;
import labs.KP.repository.LessonRepository;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private LessonRepository lessonRepository;

    public CarPojo create(CarPojo pojo) {
        Car car = new Car();
        car.setMark(pojo.getMark());
        car.setModel(pojo.getModel());
        car.setYearOfProduction(pojo.getYearOfProduction());
        car.setLicensePlateNumber(pojo.getLicensePlateNumber());

        List<Lesson> lessons = new ArrayList<>();
        if (pojo.getLessons() != null) {
            for (LessonPojo lp : pojo.getLessons()) {
                Lesson lesson = lessonRepository.findById(lp.getId()).orElse(null);
                if (lesson != null) {
                    lesson.setCar(car);
                    lessons.add(lesson);
                }
            }
        }
        car.setLessons(lessons);

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

    @Transactional
    public CarPojo update(CarPojo pojo) {
        Car car = carRepository.findById(pojo.getId()).orElse(null);
        if (car == null) {
            return null;
        }

        car.setMark(pojo.getMark());
        car.setModel(pojo.getModel());
        car.setYearOfProduction(pojo.getYearOfProduction());
        car.setLicensePlateNumber(pojo.getLicensePlateNumber());

        for (Lesson lesson : car.getLessons()) {
            lesson.setCar(null);
        }
        car.getLessons().clear();

        List<LessonPojo> lessonPojo = pojo.getLessons();
        if (lessonPojo != null) {
            for (LessonPojo lp : lessonPojo) {
                Lesson lesson = lessonRepository.findById(lp.getId()).orElse(null);
                if (lesson != null) {
                    lesson.setCar(car);
                    car.getLessons().add(lesson);
                }
            }
        }

        Car saved = carRepository.save(car);
        return CarPojo.fromEntity(saved);
    }

    @Transactional
    public void deleteById(Integer id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            for (Lesson lesson : car.getLessons()) {
                lesson.setCar(null);
                lessonRepository.save(lesson);
            }
            carRepository.deleteById(id);
        }
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
