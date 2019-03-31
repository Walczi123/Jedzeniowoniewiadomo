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
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "user", "password")) {
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
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "user", "password")) {
            //Query 1
            PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM recipes WHERE recipe_id=?");
            statement1.setInt(1, recipeId);
            //Query 2
            PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM products_recipes WHERE recipe_id=?");
            statement2.setInt(1, recipeId);
            //Execute Queries
            ResultSet r1 = statement1.executeQuery(),r2= statement2.executeQuery();
            r1.next();
            //Save data
            result.setName(r1.getString("name"));
            result.setDescription(r1.getString("description"));
            result.setUserId(r1.getInt("user_id"));
            while (r2.next()) {
                result.addProduct(r2.getInt("product_id"));
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
}
