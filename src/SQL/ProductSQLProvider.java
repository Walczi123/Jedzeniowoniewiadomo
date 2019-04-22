package SQL;

import Models.Product;
import Models.Recipe;
import SQLTablesNames.ProductTable;
import SQLTablesNames.ProductsRecipesTable;
import SQLTablesNames.RecipeTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductSQLProvider {
    private DatabaseProvider datbaseProvider;

    public  ProductSQLProvider(DatabaseProvider databaseProvider) {  this.datbaseProvider=databaseProvider;     }

    public Product getProduct(int productId) throws SQLException {
        //DESCRIPTION: return product of given id (productId)
        Product result=new Product();
        Connection c=datbaseProvider.getConnection();

        //Query
        var statement=c.prepareStatement("SELECT * FROM "+ProductTable.TABLE_NAME+" WHERE "+ProductTable.PRODUCT_ID+"=?");
        statement.setInt(1, productId);
        //Execute Queries
        var result_record=statement.executeQuery();
        //Save data
        result_record.next();
        result.setName(result_record.getString(ProductTable.NAME));
        result.setDeleted(result_record.getBoolean(ProductTable.IS_DELETED));
        return result;
    }

    public void insertProduct(Product product) throws SQLException {
        //DESCRIPTION: add product to the products table
        Connection c=datbaseProvider.getConnection();
        var statement=c.prepareStatement("INSERT INTO "+ProductTable.TABLE_NAME+" ("+ProductTable.NAME+") VALUES (?)");
        statement.setString(1, product.getName().toLowerCase());
        statement.executeUpdate();
    }

    public void deleteProduct(int productId) throws SQLException{
        //connecting
        Connection connection=datbaseProvider.getConnection();
        //Query 1
        var statement=connection.prepareStatement("UPDATE "+ProductTable.TABLE_NAME+" SET "+ProductTable.IS_DELETED+"=? WHERE product_id=?");
        statement.setBoolean(1,true);
        statement.setInt(2, productId);
        //execute the delete statement
        statement.executeUpdate();
    }

    public void restoreProduct(int productId) throws SQLException{
        //connecting
        Connection connection=datbaseProvider.getConnection();
        //Query 1
        var statement=connection.prepareStatement("UPDATE "+ProductTable.TABLE_NAME+" SET "+ProductTable.IS_DELETED+"=? WHERE product_id=?");
        statement.setBoolean(1,false);
        statement.setInt(2, productId);
        //execute the delete statement
        statement.executeUpdate();
    }

    public void updateProduct(int productId, Product product)throws SQLException{
        //connecting
        Connection connection=datbaseProvider.getConnection();
        //Query 1
        var statement=connection.prepareStatement("UPDATE "+ProductTable.TABLE_NAME+" SET "+ProductTable.NAME+"=?,"+ProductTable.IS_DELETED+"=? WHERE product_id=?");
        statement.setString(1,product.getName());
        statement.setBoolean(2,product.getDeleted());
        statement.setInt(3,productId);
        //execute the update statement
        statement.executeUpdate();
    }
}


