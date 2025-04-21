package labs.KP.pojo;

import java.sql.Date;
import labs.KP.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentPojo {
   private int id;
    private Date dateOfBirth;
    private String fio;
    private String phone;
    private String groupNumber;

    
    public static StudentPojo fromEntity(Student student) {
        if (student == null) {
            return null;
        }
        StudentPojo pojo = new StudentPojo();
        pojo.setId(student.getId());
        pojo.setDateOfBirth(student.getDateOfBirth());
        pojo.setFio(student.getFio());
        pojo.setPhone(student.getPhone());
        pojo.setGroupNumber(student.getGroupNumber());
        return pojo;
    }

    public static Student toEntity(StudentPojo pojo) {
        if (pojo == null) {
            return null;
        }
        Student student = new Student();
        student.setId(pojo.getId());
        student.setDateOfBirth(pojo.getDateOfBirth());
        student.setFio(pojo.getFio());
        student.setPhone(pojo.getPhone());
        student.setGroupNumber(pojo.getGroupNumber());
        return student;
    }
}
