package labs.KP.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import labs.KP.entity.Category;
import labs.KP.pojo.CategoryPojo;
import labs.KP.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public CategoryPojo create(CategoryPojo pojo) {
        Category category = CategoryPojo.toEntity(pojo);
        Category saved = categoryRepository.save(category);
        return CategoryPojo.fromEntity(saved);
    }
    public CategoryPojo findById(String name) {
        Optional<Category> category = categoryRepository.findById(name);
        return CategoryPojo.fromEntity(category.orElse(null));
    }
    public List<CategoryPojo> findAll() {
       List<Category> categories = categoryRepository.findAll();
        List<CategoryPojo> pojos = new ArrayList<>();
        for (Category category : categories) {
            pojos.add(CategoryPojo.fromEntity(category));
        }
        return pojos;
    }
    public CategoryPojo update(CategoryPojo pojo) {
        Category category = CategoryPojo.toEntity(pojo);
        Category updated = categoryRepository.save(category);
        return CategoryPojo.fromEntity(updated);
    }
    @Transactional
    public void deleteById(String name) {
        categoryRepository.deleteById(name);
    }
}
