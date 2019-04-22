package Models;

/**
 * Created on 20.04.19
 */
public class Category {
    private Integer id;
    private String name;
    private Integer typeId;
    private String icon;

    public Category(Integer id, String name, Integer typeId) {
        this(id,name, typeId, null);
    }

    public Category(Integer id, String name, Integer typeId, String icon) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.icon = icon;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", icon='" + icon + '\'' +
                '}';
    }
}
