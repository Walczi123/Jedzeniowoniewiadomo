package Models;

import java.util.HashMap;

/**
 * Created on 31.03.19
 */
public class Unit {
    private Integer id;
    protected String name;
    private HashMap<Unit, Double> ratios;

    public Unit(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addRatio(Unit unit, Double ratio){
        ratios.put(unit, ratio);
    }

    public Double getRatio(Unit unit){
        return ratios.get(unit);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ratios=" + ratios +
                '}';
    }
}
