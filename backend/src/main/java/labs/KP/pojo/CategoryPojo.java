package labs.KP.pojo;


import labs.KP.entity.Category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryPojo {
    private String name;
    public static CategoryPojo fromEntity(Category category) {
        if (category == null) {
            return null;
        }
        CategoryPojo pojo = new CategoryPojo();
        pojo.setName(category.getName());
        return pojo;
    }

    public static Category toEntity(CategoryPojo pojo) {
        if (pojo == null) {
            return null;
        }
        Category category = new Category();
        category.setName(pojo.getName());
        return category;
    }
}
