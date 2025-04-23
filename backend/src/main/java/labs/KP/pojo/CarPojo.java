package labs.KP.pojo;

import java.util.ArrayList;
import java.util.List;

import labs.KP.entity.Car;
import labs.KP.entity.Lesson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarPojo {
   private int id;
    private String mark;
    private String model;
    private int yearOfProduction;
    private String licensePlateNumber;
    private List<LessonPojo> lessons = new ArrayList<>();
    
    public static CarPojo fromEntity(Car car) {
        if (car == null) {
            return null;
        }
        CarPojo pojo = new CarPojo();
        pojo.setId(car.getId());
        pojo.setMark(car.getMark());
        pojo.setModel(car.getModel());
        pojo.setYearOfProduction(car.getYearOfProduction());
        pojo.setLicensePlateNumber(car.getLicensePlateNumber());
        List<LessonPojo> lessons = new ArrayList<>();
        for(Lesson lesson : car.getLessons())
        {
            lessons.add(LessonPojo.fromEntity(lesson));
        }
        pojo.setLessons(lessons);
        return pojo;
    }

    public static Car toEntity(CarPojo pojo) {
        if (pojo == null) {
            return null;
        }
        Car car = new Car();
        car.setId(pojo.getId());
        car.setMark(pojo.getMark());
        car.setModel(pojo.getModel());
        car.setYearOfProduction(pojo.getYearOfProduction());
        car.setLicensePlateNumber(pojo.getLicensePlateNumber());
        // List<Lesson> lessons = new ArrayList<>();
        // if (pojo.getLessons() != null) {
        //     for (LessonPojo lessonPojo : pojo.getLessons()) {
        //         lessons.add(LessonPojo.toEntity(lessonPojo));
        //     }
        // }
        // car.setLessons(lessons);
        return car;
    }
}
