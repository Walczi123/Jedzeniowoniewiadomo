package SQL;

import Models.Recipe;
import SQLTablesNames.RecipeTable;
import SQLTablesNames.ProductsRecipesTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class RecipeSQLProvider {
    private DatabaseProvider datbaseProvider;
    public RecipeSQLProvider(DatabaseProvider databaseProvider) {
        this.datbaseProvider=databaseProvider;
    }
    public Recipe getRecipe(int recipeId) throws SQLException {
        //DESCRIPTION: return recipe of given id (recipeId)
        Recipe result=new Recipe();
        Connection connection=datbaseProvider.getConnection();
        //Query 1
        var statement_rec=connection.prepareStatement("SELECT * FROM "+RecipeTable.TABLE_NAME+" WHERE "+RecipeTable.RECIPE_ID+"=?");
        statement_rec.setInt(1, recipeId);
        //Query 2
        var statement_prod=connection.prepareStatement("SELECT * FROM "+ProductsRecipesTable.TABLE_NAME+" WHERE "+ProductsRecipesTable.RECIPE_ID+"=?");
        statement_prod.setInt(1, recipeId);
        //Execute Queries
        var result_rec=statement_rec.executeQuery();
        var result_prod=statement_prod.executeQuery();
        //Save data
        result_rec.next();
        result.setName(result_rec.getString(RecipeTable.NAME));
        result.setDescription(result_rec.getString(RecipeTable.DESCRIPTION));
        result.setUserId(result_rec.getInt(RecipeTable.USER_ID));
        result.setDeleted(result_rec.getBoolean(RecipeTable.IS_DELETED));
        while (result_prod.next()) {
            result.addProduct(result_prod.getInt(ProductsRecipesTable.PRODUCT_ID));
        }
        return result;
    }
    public List<Recipe> getAllUserRecipes(int userId) throws SQLException{
        //DESCRIPTION: return all recipes of user (userId)
        List<Recipe> result=new ArrayList<Recipe>();
        //connecting
        Connection connection=datbaseProvider.getConnection();
        //Query
        var statement=connection.prepareStatement("SELECT * FROM "+RecipeTable.TABLE_NAME+" WHERE "+RecipeTable.USER_ID+"=?");
        statement.setInt(1, userId);
        //Execute Query
        var r=statement.executeQuery();
        //Save data
        while (r.next()) {
            result.add(getRecipe(r.getInt(RecipeTable.RECIPE_ID)));
        }
        return result;
    }
    public void insertRecipe(Recipe input)throws SQLException {
        //connecting
        Connection connection=datbaseProvider.getConnection();
        //SQL command
        String insertSQLRecipe="INSERT INTO "+RecipeTable.TABLE_NAME+" ("+RecipeTable.NAME+","+RecipeTable.DESCRIPTION+","+RecipeTable.USER_ID+") VALUES (?,?,?)";
        String insertSQLProduct_recipe="INSERT INTO "+ProductsRecipesTable.TABLE_NAME+" ("+ProductsRecipesTable.PRODUCT_ID+","+ProductsRecipesTable.RECIPE_ID+","+ProductsRecipesTable.AMOUNT+","+ProductsRecipesTable.UNIT_ID+") VALUES (?,?,1,1)";
        var statement_rec=connection.prepareStatement(insertSQLRecipe,Statement.RETURN_GENERATED_KEYS);
        var statement_prodrec=connection.prepareStatement(insertSQLProduct_recipe);
        //input variables in statement for table recipe
        statement_rec.setString(1, input.getName());
        statement_rec.setString(2, input.getDescription());
        statement_rec.setInt(3, input.getUserId());
        //execute SQL statement for table recipe
        statement_rec.executeUpdate();
        //getting id of added record
        var res=statement_rec.getGeneratedKeys();
        int recordId=-1;
        if(res.next())
            recordId=res.getInt(1);
        if(recordId==-1)
            throw new SQLException("Creating unit failed, no ID obtained.");
        //input variables in statement for table product_recipe
        for(var record:input.getProductsList()) {
            statement_prodrec.setInt(1, record);
            statement_prodrec.setInt(2, recordId);
            //execute SQL statement for table product_recipe in every iteration
            statement_prodrec.executeUpdate();
        }
    }
    public void deleteRecipe(int recipeId)throws SQLException{
        //connecting
        Connection connection=datbaseProvider.getConnection();
        //Query 1
        var statement=connection.prepareStatement("UPDATE "+RecipeTable.TABLE_NAME+" SET "+RecipeTable.IS_DELETED+"=? WHERE recipe_id=?");
        statement.setBoolean(1,true);
        statement.setInt(2, recipeId);
        //execute the delete statement
        statement.executeUpdate();
    }
    public void updateRecipe(int recipeId, Recipe recipe)throws SQLException{
        //connecting
        Connection connection=datbaseProvider.getConnection();
        //Query 1
        PreparedStatement statement=connection.prepareStatement("UPDATE "+RecipeTable.TABLE_NAME+" SET "+RecipeTable.NAME+"=?,"+RecipeTable.DESCRIPTION+"=?,"+RecipeTable.USER_ID+"=?,"+RecipeTable.IS_DELETED+"=? WHERE recipe_id=?");
        statement.setString(1,recipe.getName());
        statement.setString(2,recipe.getDescription());
        statement.setInt(3,recipe.getUserId());
        statement.setBoolean(4,recipe.getDeleted());
        statement.setInt(5, recipeId);
        //execute the update statement
        statement.executeUpdate();
    }
}
