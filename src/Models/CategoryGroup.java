package Models;

import java.util.HashMap;

/**
 * Created on 20.04.19
 */
public class CategoryGroup {
    private Integer id;
    private String name;
    private HashMap<Integer, Category> categories;

    public CategoryGroup(Integer id, String name, HashMap<Integer, Category> categories) {
        this.id = id;
        this.name = name;
        this.categories = categories;
    }

    public CategoryGroup(Integer id, String name) {
        this(id, name, null);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashMap<Integer, Category> getCategories() {
        return categories;
    }

    public Category getCategory(Integer id){
        return categories.get(id);
    }

    public void addCategory(Category category){
        this.categories.put(category.getId(), category);
    }

    @Override
    public String toString() {
        return "CategoryGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                '}';
    }
}
