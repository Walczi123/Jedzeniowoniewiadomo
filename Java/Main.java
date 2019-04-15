import Models.Recipe;
import SQL.PostgresDatabaseProvider;
import SQL.RecipeSQLProvider;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            PostgresDatabaseProvider databaseLoader = new PostgresDatabaseProvider("localhost:5432/Jedzenioweniewiadomo", "user", "password");
            databaseLoader.open();
            RecipeSQLProvider rp = new RecipeSQLProvider(databaseLoader);
            List<Recipe> recipeList = rp.getAllUserRecipe(10);
            for (var a:recipeList) {
                System.out.println(a.getName());
            }
            databaseLoader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}