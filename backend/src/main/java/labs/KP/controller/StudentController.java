package labs.KP.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import labs.KP.pojo.StudentPojo;
import labs.KP.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping
    public ResponseEntity<StudentPojo> create(@RequestBody StudentPojo pojo) {
        return new ResponseEntity<>(studentService.create(pojo), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        StudentPojo student = studentService.findById(id);
        if (student == null) { 
            return new ResponseEntity<>("Студент не найден", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StudentPojo>> findAll() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StudentPojo> update(@RequestBody StudentPojo pojo) {
        return new ResponseEntity<>(studentService.update(pojo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        StudentPojo student = studentService.findById(id);
        if (student == null) {
            return new ResponseEntity<>("Студент не найден", HttpStatus.NOT_FOUND);
        }
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(required = false) Date dateOfBirth,@RequestParam(required = false) String fio,@RequestParam(required = false) String phone,@RequestParam(required = false) String groupNumber) {
        List<StudentPojo> students = studentService.search(dateOfBirth, fio, phone, groupNumber);
        if(students==null)
        {
            return new ResponseEntity<>("Студент не найден", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}
