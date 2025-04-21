package labs.KP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import labs.KP.pojo.CarPojo;
import labs.KP.service.CarService;

@RestController
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    private CarService carService;
    @PostMapping
    public ResponseEntity<CarPojo> create(@RequestBody CarPojo pojo) {
        return new ResponseEntity<>(carService.create(pojo), HttpStatus.OK);
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

    @PutMapping
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

    @GetMapping("/mark/{mark}")
    public ResponseEntity<List<CarPojo>> findByMark(@PathVariable String mark) {
        return new ResponseEntity<>(carService.findByMark(mark), HttpStatus.OK);
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<List<CarPojo>> findByModel(@PathVariable String model) {
        return new ResponseEntity<>(carService.findByModel(model), HttpStatus.OK);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<CarPojo>> findByYear(@PathVariable Integer year) {
        return new ResponseEntity<>(carService.findByYearOfProduction(year), HttpStatus.OK);
    }

    @GetMapping("/plate/{plate}")
    public ResponseEntity<?> findByPlate(@PathVariable String plate) {
        CarPojo car = carService.findByLicensePlateNumber(plate);
        if (car == null) { 
            return new ResponseEntity<>("Автомобиль не найден", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
}
