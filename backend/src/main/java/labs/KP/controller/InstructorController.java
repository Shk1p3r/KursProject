package labs.KP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import labs.KP.pojo.InstructorPojo;
import labs.KP.service.InstructorService;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;
    
    @PostMapping
    public ResponseEntity<InstructorPojo> create(@RequestBody InstructorPojo pojo) {
        return new ResponseEntity<>(instructorService.create(pojo), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        InstructorPojo instructor = instructorService.findById(id);
        if (instructor == null) { 
            return new ResponseEntity<>("Инструктор не найден", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InstructorPojo>> findAll() {
        return new ResponseEntity<>(instructorService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<InstructorPojo> update(@RequestBody InstructorPojo pojo) {
        return new ResponseEntity<>(instructorService.update(pojo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        InstructorPojo instructor = instructorService.findById(id);
        if (instructor == null) { 
            return new ResponseEntity<>("Инструктор не найден", HttpStatus.NOT_FOUND);
        }
        instructorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/fio/{fio}")
    public ResponseEntity<List<InstructorPojo>> findByFio(@PathVariable String fio) {
        return new ResponseEntity<>(instructorService.findByFio(fio), HttpStatus.OK);
    }

    @GetMapping("/seniority/{seniority}")
    public ResponseEntity<List<InstructorPojo>> findBySeniority(@PathVariable Integer seniority) {
        return new ResponseEntity<>(instructorService.findBySeniority(seniority), HttpStatus.OK);
    }
}
