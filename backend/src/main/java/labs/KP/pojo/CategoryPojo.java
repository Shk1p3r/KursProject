package labs.KP.pojo;

import java.util.ArrayList;
import java.util.List;

import labs.KP.entity.Category;
import labs.KP.entity.Instructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryPojo {
    private String name;
    private List<InstructorPojo> instructors = new ArrayList<>();

    public static CategoryPojo fromEntity(Category category) {
        if (category == null) {
            return null;
        }
        CategoryPojo pojo = new CategoryPojo();
        pojo.setName(category.getName());
        List<InstructorPojo> instructors = new ArrayList<>();
        for (Instructor instructor : category.getInstructors()) {
            instructors.add(InstructorPojo.fromEntity(instructor));
        }
        pojo.setInstructors(instructors);
        return pojo;
    }

    public static Category toEntity(CategoryPojo pojo) {
        if (pojo == null) {
            return null;
        }
        Category category = new Category();
        category.setName(pojo.getName());
        List<Instructor> instructors = new ArrayList<>();
        if (pojo.getInstructors() != null) {
            for (InstructorPojo instructorPojo : pojo.getInstructors()) {
                Instructor instructor = InstructorPojo.toEntity(instructorPojo);
                instructor.setCategory(category);
                instructors.add(instructor);
            }
        }
        category.setInstructors(instructors);
        return category;
    }
}
