package labs.KP.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import labs.KP.entity.Category;
import labs.KP.entity.Instructor;
import labs.KP.pojo.CategoryPojo;
import labs.KP.pojo.InstructorPojo;
import labs.KP.repository.InstructorRepository;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    public InstructorPojo create(InstructorPojo pojo) {
        Instructor instructor = InstructorPojo.toEntity(pojo);
        Instructor saved = instructorRepository.save(instructor);
        return InstructorPojo.fromEntity(saved);
    }

    public InstructorPojo findById(Integer id) {
        Instructor instructor = instructorRepository.findById(id).orElse(null);
        return InstructorPojo.fromEntity(instructor);
    }

    public List<InstructorPojo> findAll() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<InstructorPojo> pojos = new ArrayList<>();
        for (Instructor instructor : instructors) {
            pojos.add(InstructorPojo.fromEntity(instructor));
        }
        return pojos;
    }

    public InstructorPojo update(InstructorPojo pojo) {
        Instructor instructor = InstructorPojo.toEntity(pojo);
        Instructor updated = instructorRepository.save(instructor);
        return InstructorPojo.fromEntity(updated);
    }
    @Transactional
    public void deleteById(Integer id) {
        instructorRepository.deleteById(id);
    }

    public List<InstructorPojo> findByFio(String fio) {
        List<Instructor> instructors = instructorRepository.findByFioContainingIgnoreCase(fio);
        List<InstructorPojo> pojos = new ArrayList<>();
        for (Instructor instructor : instructors) {
            pojos.add(InstructorPojo.fromEntity(instructor));
        }
        return pojos;
    }

    public List<InstructorPojo> findBySeniority(Integer seniority) {
        List<Instructor> instructors = instructorRepository.findBySeniority(seniority);
        List<InstructorPojo> pojos = new ArrayList<>();
        for (Instructor instructor : instructors) {
            pojos.add(InstructorPojo.fromEntity(instructor));
        }
        return pojos;
    }

    public List<InstructorPojo> findByCategory(CategoryPojo categoryPojo) {
        Category category = CategoryPojo.toEntity(categoryPojo);
        List<Instructor> instructors = instructorRepository.findByCategory(category);
        List<InstructorPojo> pojos = new ArrayList<>();
        for (Instructor instructor : instructors) {
            pojos.add(InstructorPojo.fromEntity(instructor));
        }
        return pojos;
    }
}
