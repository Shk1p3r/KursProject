package labs.KP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import labs.KP.pojo.LessonPojo;
import labs.KP.service.LessonService;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {
    @Autowired
    private LessonService lessonService;
    @PostMapping
    public ResponseEntity<LessonPojo> create(@RequestBody LessonPojo pojo) {
        return new ResponseEntity<>(lessonService.create(pojo), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        LessonPojo lesson = lessonService.findById(id);
        if (lesson == null) { 
            return new ResponseEntity<>("Занятие не найдено", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LessonPojo>> findAll() {
        return new ResponseEntity<>(lessonService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LessonPojo> update(@RequestBody LessonPojo pojo) {
        return new ResponseEntity<>(lessonService.update(pojo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        LessonPojo lesson = lessonService.findById(id);
        if (lesson == null) { 
            return new ResponseEntity<>("Занятие не найдено", HttpStatus.NOT_FOUND);
        }
        lessonService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
