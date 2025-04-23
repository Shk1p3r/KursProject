package labs.KP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import labs.KP.pojo.CarPojo;
import labs.KP.service.CarService;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CarPojo pojo) {
        CarPojo car = carService.create(pojo);
        if(car == null)
        {
            return new ResponseEntity<>("Автомобиль с таким номерным знаком существует", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        CarPojo car = carService.findById(id);
        if (car == null) { 
            return new ResponseEntity<>("Автомобиль не найден", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CarPojo>> findAll() {
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<CarPojo> update(@RequestBody CarPojo pojo) {
        return new ResponseEntity<>(carService.update(pojo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        CarPojo car = carService.findById(id);
        if (car == null) {
            return new ResponseEntity<>("Автомобиль не найден", HttpStatus.NOT_FOUND);
        }
        carService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchCars(@RequestParam(required = false) String mark,@RequestParam(required = false) String model,@RequestParam(required = false) Integer year,@RequestParam(required = false) String plate) {
        List<CarPojo> cars = carService.search(mark, model, year, plate);
        if(cars==null)
        {
            return new ResponseEntity<>("Автомобиль не найден", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

}
