import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Recipe {
    private String name;
    private String description;
    private List<Integer> productsList;
    private int  userId;
//getters and setters
    //name
    public void setName(String s){this.name=s;}
    public String getName(){return this.name;}
    //description
    public void setDescription(String s){this.description=s;}
    public String getDescription(){return this.description;}
    //productList
    public void setProductsList(List<Integer> l){this.productsList=l;}
    public List<Integer> getProductsList(){return this.productsList;}
    //userId
    public void setUserId(int u){this.userId=u;}
    public int getUserId(){return this.userId;}
//constructors
    public Recipe(){}
    public Recipe(String n,String desc,List<Integer> list,int id){
        this.name=n;
        this.description=desc;
        this.productsList=list;
        this.userId=id;
    }
//methods
    void getFullRecipe(int recipeId){
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "user", "password")) {
            Statement statement = connection.createStatement();;
            ResultSet r = statement.executeQuery("SELECT * FROM recipes");
/*            PreparedStatement statement = connection.prepareStatement("SELECT * FROM recipes");
            statement.setString(1, userID);*/
            while (r.next()) {
                if(r.getInt("recipe_id")== recipeId){
                    this.setName(r.getString("name"));
                    this.setDescription(r.getString("description"));
                    this.setUserId(r.getInt("user_id"));
                }
            }
            List<Integer> list= new ArrayList<>();
            r = statement.executeQuery("SELECT * FROM products_recipes");
            while (r.next()) {
                if(r.getInt("recipe_id")== recipeId){
                    list.add(r.getInt("product_id"));
                }
            }
            this.setProductsList(list);
        }
        catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}
