package UI;

import SQL.PostgresDatabaseProvider;
import SQL.UnitSQLProvider;
import Models.Unit;

import java.util.HashMap;

/**
 * Created on 31.03.19
 */
public class Main {
    public static void main(String[] args) {
        try {
            PostgresDatabaseProvider databaseLoader = new PostgresDatabaseProvider("localhost:5432/jedzeniowoniewiadomo", "jedzeniowo", "Pass#1234");
            databaseLoader.open();
            UnitSQLProvider unitLoader = new UnitSQLProvider(databaseLoader);
            Unit unit=unitLoader.get(1);
            HashMap<Integer, Unit> units = unitLoader.getAll();
            for(var unitKey: units.keySet()){
                System.out.println(units.get(unitKey).getName());
            }
            unit = unitLoader.insert(new Unit(null, "JednostkaKuby"));
            System.out.println(unit.getName());
            unit=unitLoader.get(unit.getId());
            System.out.println(unit.getName());
            unitLoader.delete(unit);
            unit=unitLoader.get(unit.getId());
            System.out.println(unit.getName());
            databaseLoader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
