package UI;

import Models.Category;
import SQL.CategoriesSQLProvider;
import SQL.CategoryGropusSQLProvider;
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
            CategoriesSQLProvider categoriesSQLProvider = new CategoriesSQLProvider(databaseLoader);
            CategoryGropusSQLProvider categoryGropusSQLProvider = new CategoryGropusSQLProvider(databaseLoader);
            System.out.println(categoryGropusSQLProvider.getAll());
            System.out.println(categoryGropusSQLProvider.getWithData(1).getCategories());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
