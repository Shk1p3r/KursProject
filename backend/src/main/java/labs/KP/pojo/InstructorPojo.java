package labs.KP.pojo;

import labs.KP.entity.Instructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorPojo {
private int id;
    private String fio;
    private int seniority;
    private CategoryPojo category;
    public static InstructorPojo fromEntity(Instructor instructor) {
        if (instructor == null) {
            return null;
        }
        InstructorPojo pojo = new InstructorPojo();
        pojo.setId(instructor.getId());
        pojo.setFio(instructor.getFio());
        pojo.setSeniority(instructor.getSeniority());
        pojo.setCategory(CategoryPojo.fromEntity(instructor.getCategory()));
        return pojo;
    }

    public static Instructor toEntity(InstructorPojo pojo) {
        if (pojo == null) {
            return null;
        }
        Instructor instructor = new Instructor();
        instructor.setId(pojo.getId());
        instructor.setFio(pojo.getFio());
        instructor.setSeniority(pojo.getSeniority());
        instructor.setCategory(CategoryPojo.toEntity(pojo.getCategory()));
        return instructor;
    }
}
