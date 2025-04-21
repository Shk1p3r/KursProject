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
@RequestMapping("/api/student")
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

    @GetMapping("/fio/{fio}")
    public ResponseEntity<List<StudentPojo>> findByFio(@PathVariable String fio) {
        return new ResponseEntity<>(studentService.findByFio(fio), HttpStatus.OK);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<StudentPojo>> findByPhone(@PathVariable String phone) {
        return new ResponseEntity<>(studentService.findByPhone(phone), HttpStatus.OK);
    }

    @GetMapping("/group/{groupNumber}")
    public ResponseEntity<List<StudentPojo>> findByGroup(@PathVariable String groupNumber) {
        return new ResponseEntity<>(studentService.findByGroupNumber(groupNumber), HttpStatus.OK);
    }

    @GetMapping("/dateOfBirth/{dateOfBirth}")
    public ResponseEntity<List<StudentPojo>> findByDateOfBirth(@PathVariable Date dateOfBirth) {
        return new ResponseEntity<>(studentService.findByDateOfBirth(dateOfBirth), HttpStatus.OK);
    }
}
