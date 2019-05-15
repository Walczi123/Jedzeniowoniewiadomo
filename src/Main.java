import Models.Price;
import Models.Product;
import Models.Recipe;
import SQL.PostgresDatabaseProvider;
import SQL.PriceSQLProvider;
import SQL.ProductSQLProvider;
import SQL.RecipeSQLProvider;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            PostgresDatabaseProvider databaseLoader = new PostgresDatabaseProvider("localhost:5432/Jedzenioweniewiadomo", "user", "password");
            databaseLoader.open();
            PriceSQLProvider rp = new PriceSQLProvider(databaseLoader);
           //  Price pric= new Price(0.25, 3);
          //  System.out.println(pric.getValue());
            //rp.updatePrice(3, pric);
           Price pric= rp.getPrice(3);


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