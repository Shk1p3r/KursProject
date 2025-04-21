package labs.KP.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import labs.KP.pojo.ExamPojo;
import labs.KP.service.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
    @Autowired
    private ExamService examService;
    @PostMapping
    public ResponseEntity<ExamPojo> create(@RequestBody ExamPojo pojo) {
        return new ResponseEntity<>(examService.create(pojo), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        ExamPojo exam = examService.findById(id);
        if (exam == null) { 
            return new ResponseEntity<>("Экзамен не найден", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ExamPojo>> findAll() {
        return new ResponseEntity<>(examService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ExamPojo> update(@RequestBody ExamPojo pojo) {
        return new ResponseEntity<>(examService.update(pojo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ExamPojo exam = examService.findById(id);
        if (exam == null) { 
            return new ResponseEntity<>("Экзамен не найден", HttpStatus.NOT_FOUND);
        }
        examService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<ExamPojo>> findByDate(@PathVariable Date date) {
        return new ResponseEntity<>(examService.findByDate(date), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ExamPojo>> findByType(@PathVariable String type) {
        return new ResponseEntity<>(examService.findByTypeOfExams(type), HttpStatus.OK);
    }

    @GetMapping("/result/{result}")
    public ResponseEntity<List<ExamPojo>> findByResult(@PathVariable String result) {
        return new ResponseEntity<>(examService.findByResult(result), HttpStatus.OK);
    }
}
