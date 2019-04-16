import Models.Product;
import Models.Recipe;
import SQL.PostgresDatabaseProvider;
import SQL.ProductSQLProvider;
import SQL.RecipeSQLProvider;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            PostgresDatabaseProvider databaseLoader = new PostgresDatabaseProvider("localhost:5432/Jedzenioweniewiadomo", "user", "password");
            databaseLoader.open();
            ProductSQLProvider rp = new ProductSQLProvider(databaseLoader);
             Product prod = rp.getProduct(3);
            System.out.println(prod.getName());
            Product pr = new Product("marchew");
            rp.updateProduct(3, pr);


    /*
            RecipeSQLProvider rp = new RecipeSQLProvider(databaseLoader);
            List<Recipe> recipeList = rp.getAllUserRecipe(3);
            for (var a:recipeList) {
                System.out.println(a.getName());
            } */
            databaseLoader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}