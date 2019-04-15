package SQL;

import Models.Recipe;
import SQLTablesNames.RecipeTable;
import SQLTablesNames.ProductsRecipesTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RecipeSQLProvider {
    private DatabaseProvider datbaseProvider;

    public  RecipeSQLProvider(DatabaseProvider databaseProvider) {
        this.datbaseProvider = databaseProvider;
    }

    public Recipe getRecipe(int recipeId) throws SQLException {
        //DESCRIPTION: return recipe of given id (recipeId)
        Recipe result=new Recipe();
        Connection c = datbaseProvider.getConnection();
        //Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Jedzenioweniewiadomo", "user", "password");
        //Query 1
        var stmt_rec = c.prepareStatement("SELECT * FROM "+ RecipeTable.TABLE_NAME +" WHERE "+RecipeTable.RECIPE_ID+"=?");
        stmt_rec.setInt(1, recipeId);
        //Query 2
        var stmt_prod = c.prepareStatement("SELECT * FROM "+ ProductsRecipesTable.TABLE_NAME +" WHERE "+ProductsRecipesTable.RECIPE_ID+"=?");
        stmt_prod.setInt(1, recipeId);
        //Execute Queries
        var result_rec = stmt_rec.executeQuery();
        var result_prod= stmt_prod.executeQuery();
        //Save data
        result_rec.next();
        result.setName(result_rec.getString(RecipeTable.NAME));
        result.setDescription(result_rec.getString(RecipeTable.DESCRIPTION));
        result.setUserId(result_rec.getInt(RecipeTable.USER_ID));
        while (result_prod.next()) {
            result.addProduct(result_prod.getInt(ProductsRecipesTable.PRODUCT_ID));
        }
        return result;
    }

    public List<Recipe> getAllUserRecipe(int userId) throws SQLException{
        //DESCRIPTION: return all recipes of user (userId)
        List<Recipe> result= new ArrayList<Recipe>();
        //connecting
        Connection c = datbaseProvider.getConnection();
        //Query
        var stmt = c.prepareStatement("SELECT * FROM "+RecipeTable.TABLE_NAME+" WHERE "+RecipeTable.USER_ID+"=?");
        stmt.setInt(1, userId);
        //Execute Query
        var r = stmt.executeQuery();
        //Save data
        while (r.next()) {
            result.add(getRecipe(r.getInt(RecipeTable.RECIPE_ID)));
        }
        return result;
    }
}
