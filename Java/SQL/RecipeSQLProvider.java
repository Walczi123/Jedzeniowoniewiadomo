package SQL;

import Models.Recipe;
import SQLTablesNames.RecipeTable;
import SQLTablesNames.ProductsRecipesTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class RecipeSQLProvider {
    private DatabaseLoader datbaseLoader;

    public  RecipeSQLProvider(DatabaseLoader databaseLoader) {
        this.datbaseLoader = databaseLoader;
    }

    Recipe getRecipe(int recipeId) throws SQLException {
        Recipe result=new Recipe();
        Connection c = datbaseLoader.getConnection();
        //Query 1
        var stmt_rec = c.prepareStatement("SELECT * FROM "+ RecipeTable.TABLE_NAME +"WHERE "+RecipeTable.USER_ID+"=?");
        stmt_rec.setInt(1, recipeId);
        //Query 2
        var stmt_prod = c.prepareStatement("SELECT * FROM "+ ProductsRecipesTable.TABLE_NAME +" WHERE"+RecipeTable.USER_ID+"=?");
        stmt_prod.setInt(1, recipeId);
        //Execute Queries
        var result_rec = stmt_rec.executeQuery();
        var result_prod= stmt_prod.executeQuery();
        //Save data
        result_rec.next();
        result.setName(result_rec.getString("name"));
        result.setDescription(result_rec.getString("description"));
        result.setUserId(result_rec.getInt("user_id"));
        while (result_prod.next()) {
            result.addProduct(result_prod.getInt("product_id"));
        }
        return result;
    }
}
