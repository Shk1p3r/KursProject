package labs.KP.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import labs.KP.pojo.CategoryPojo;
import labs.KP.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryPojo> create(@RequestBody CategoryPojo pojo) {
        return new ResponseEntity<>(categoryService.create(pojo), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findById(@PathVariable String name) {
        CategoryPojo category = categoryService.findById(name);
        if (category == null) {
            return new ResponseEntity<>("Категория не найдена", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryPojo>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoryPojo> update(@RequestBody CategoryPojo pojo) {
        return new ResponseEntity<>(categoryService.update(pojo), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        CategoryPojo category = categoryService.findById(name);
        if (category == null) {
            return new ResponseEntity<>("Категория не найдена", HttpStatus.NOT_FOUND);
        }
        categoryService.deleteById(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
