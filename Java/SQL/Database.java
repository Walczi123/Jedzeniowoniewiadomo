import Models.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public Database(){}
    //Recipe PART
    List<Recipe> getAllUserRecipe(int userId){
        //DESCRIPTION: return all recipes of user (userId)
        //connecting
        List<Recipe> result= new ArrayList<Recipe>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Jedzenioweniewiadomo", "user", "password")) {
            //Query 1
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM recipes WHERE user_id=?");
            statement.setInt(1, userId);
            //Execute Queries
            ResultSet r = statement.executeQuery();
            //Save data
            while (r.next()) {
                result.add(getRecipe(r.getInt("recipe_id")));
            }
        }
        catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }
    Recipe getRecipe(int recipeId){
        Recipe result=new Recipe();
        //connecting
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Jedzenioweniewiadomo", "user", "password")) {

        }
        catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }
    void insertRecipe(Recipe input){
        //connecting
        try (Connection connection = DriverManager.getConnection("//localhost:5432/postgres", "user", "password")) {
            //SQL command
            String insertSQLRecipe = "INSERT INTO recipes (name, description, user_id) VALUES (?,?,?)";
            String insertSQLProduct_recipe = "INSERT INTO products_recipes (product_id, recipe_id, amount, unit_id) VALUES (?,?,1,1)";
            PreparedStatement statementRecipe = connection.prepareStatement(insertSQLRecipe),statementProduct_recipe = connection.prepareStatement(insertSQLProduct_recipe);
            //input variables in statement for table recipe
            statementRecipe.setString(1, input.getName());
            statementRecipe.setString(2, input.getDescription());
            statementRecipe.setInt(3, input.getUserId());
            //execute SQL statement for table recipe
            //statementRecipe.executeUpdate();
            //getting id of added record
/*
            long recordId= 0;
            int affectedRows = statementRecipe.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = statementRecipe.getGeneratedKeys()) {
                    if (rs.next()) {
                        recordId = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
*/          long recordId= 0;
            int i=statementRecipe.executeUpdate();
            System.out.println(i+" records inserted");
            if (i > 0) {
                // get the ID back
                try (ResultSet rs = statementRecipe.getGeneratedKeys()) {

                        rs.next();rs.next();
                        System.out.println(rs);
                        recordId = rs.getLong(1);

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            System.out.println(recordId);
            //input variables in statement for table product_recipe
/*            for(var record:input.getProductsList()){
                statementProduct_recipe.setInt(1,record);
                statementProduct_recipe.setInt(2,recordId);
                //execute SQL statement for table product_recipe in every iteration
                statementProduct_recipe.executeUpdate();
            }*/
        }
        catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
    void deleteCascadeRecipe(int recipeId){
        //connecting
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "user", "password")) {
            //Query 1
            PreparedStatement statement=connection.prepareStatement("DELETE FROM recipes WHERE recipe_id=?");
            statement.setInt(1, recipeId);
            //execute the delete statement
            statement.executeUpdate();
            }
        catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
    void updateRecipe(int recipeId, Recipe recipe){
        //connecting
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "user", "password")) {
            //Query 1
            PreparedStatement statement=connection.prepareStatement("UPDATE recipes SET name=?,description=?,user_id=? WHERE recipe_id=?");
            statement.setString(1,recipe.getName());
            statement.setString(2,recipe.getDescription());
            statement.setInt(3,recipe.getUserId());
            statement.setInt(4, recipeId);
            //execute the delete statement
            statement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }




}
