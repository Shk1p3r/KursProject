package labs.KP.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import labs.KP.entity.Category;
import labs.KP.entity.Instructor;
import labs.KP.pojo.CategoryPojo;
import labs.KP.pojo.InstructorPojo;
import labs.KP.repository.CategoryRepository;
import labs.KP.repository.InstructorRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InstructorRepository instructorRepository;

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
        // Получаем текущую категорию
        Category category = categoryRepository.findById(pojo.getName()).orElse(null);
        if (category == null) {
            return null;
        }
    
        // Обновляем базовые поля
        category.setName(pojo.getName());
    
        // Получаем список ID инструкторов из pojo
        List<Integer> newInstructorIds = new ArrayList<>();
        if (pojo.getInstructors() != null) {
            for (InstructorPojo ip : pojo.getInstructors()) {
                newInstructorIds.add(ip.getId());
            }
        }
    
        // Обрабатываем текущих инструкторов категории: отвязываем тех, кто исключён
        List<Instructor> instructorsToUnlink = new ArrayList<>();
        for (Instructor instructor : category.getInstructors()) {
            if (!newInstructorIds.contains(instructor.getId())) {
                instructor.setCategory(null);
                instructorsToUnlink.add(instructor);
            }
        }
        instructorRepository.saveAll(instructorsToUnlink);
    
        // Привязываем новых инструкторов
        List<Instructor> updatedInstructors = new ArrayList<>();
        for (Integer instructorId : newInstructorIds) {
            Instructor instructor = instructorRepository.findById(instructorId).orElse(null);
            if (instructor != null) {
                instructor.setCategory(category);
                instructorRepository.save(instructor);
                updatedInstructors.add(instructor);
            }
        }
    
        // Обновляем список в самой категории
        category.setInstructors(updatedInstructors);
    
        // Сохраняем категорию
        Category saved = categoryRepository.save(category);
    
        return CategoryPojo.fromEntity(saved);
    }

    @Transactional
    public void deleteById(String name) {
        Optional<Category> categoryOptional = categoryRepository.findById(name);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            for (Instructor instructor : category.getInstructors()) {
                instructor.setCategory(null);
            }
            categoryRepository.deleteById(name);
        }
    }
}
