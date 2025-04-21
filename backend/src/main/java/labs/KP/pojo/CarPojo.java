package labs.KP.pojo;

import labs.KP.entity.Car;
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
        return car;
    }
}
