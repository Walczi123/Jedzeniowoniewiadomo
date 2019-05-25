import Models.Price;
import Models.Product;
import Models.Recipe;
import SQL.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            PostgresDatabaseProvider databaseLoader = new PostgresDatabaseProvider("localhost:5432/Jedzenioweniewiadomo", "user", "password");
            databaseLoader.open();
            RecipeSQLProvider rl = new RecipeSQLProvider(databaseLoader);
            var r=rl.getAllUserRecipes(2);
            for(var e: r){
                System.out.println(e.getName());
            }
            databaseLoader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}