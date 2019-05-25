package SQL;

import Models.Product;
import Models.ProductInList;
import Models.Unit;
import SQLTablesNames.ProductsRecipesTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductListSQLProvider {
    private DatabaseProvider datbaseProvider;
    public ProductListSQLProvider(DatabaseProvider databaseProvider) { this.datbaseProvider=databaseProvider; }
    public List<ProductInList> getProductList(int recipeId) throws SQLException {
        //DESCRIPTION: return list of products of the recipe (recipeId)
        List<ProductInList> result=new ArrayList<ProductInList>();
        //connecting
        Connection connection=datbaseProvider.getConnection();
        //Query
        var statement=connection.prepareStatement("SELECT * FROM "+ ProductsRecipesTable.TABLE_NAME+" WHERE "+ProductsRecipesTable.RECIPE_ID+"=?");
        statement.setInt(1, recipeId);
        //Execute Query
        var r=statement.executeQuery();
        //Save data
        var prodProvider = new ProductSQLProvider(this.datbaseProvider);
        var unitProvider = new UnitSQLProvider(this.datbaseProvider);
        while (r.next())
        {
            var tmp = new ProductInList();
            tmp.setProduct(prodProvider.getProduct(r.getInt(ProductsRecipesTable.PRODUCT_ID)));
            tmp.setUnit(unitProvider.getUnit(r.getInt(ProductsRecipesTable.UNIT_ID)));
            tmp.setAmount(r.getFloat(ProductsRecipesTable.AMOUNT));
            result.add(tmp);
        }
        return result;
    }
    public void insertProductList(List<ProductInList> list,int recipeId) throws SQLException {
        //DESCRIPTION: save list of products of the recipe in database
        //connecting
        ProductSQLProvider productProvider=new ProductSQLProvider(this.datbaseProvider);
        UnitSQLProvider unitProvider=new UnitSQLProvider(this.datbaseProvider);
        Connection connection=datbaseProvider.getConnection();
        for(var elem:list){
            Product product=elem.getProduct();
            Float amount=elem.getAmount();
            Unit unit=elem.getUnit();
            int productId,unitId;
            if(!productProvider.doesProductExist(product)){
                productProvider.insertProduct(product);
            }
            productId=productProvider.getProductId(product);
            if(!unitProvider.doesUnitExist(unit)){
                unitProvider.insertUnit(unit);
            }
            unitId=unitProvider.getUnitId(unit);
            //SQL command
            String insertSQLProductInList="INSERT INTO "+ProductsRecipesTable.TABLE_NAME+
                    " ("+ProductsRecipesTable.RECIPE_ID+","+ProductsRecipesTable.PRODUCT_ID+
                    ","+ProductsRecipesTable.AMOUNT+","+ProductsRecipesTable.UNIT_ID+
                    ") VALUES (?,?,?,?)";
            var statement_prodinlist=connection.prepareStatement(insertSQLProductInList);
            //input variables in statement for table recipe
            statement_prodinlist.setInt(1, recipeId);
            statement_prodinlist.setInt(2, productId);
            statement_prodinlist.setFloat(3, amount);
            statement_prodinlist.setInt(4, unitId);
            //execute SQL statement for table recipe
            statement_prodinlist.executeUpdate();
        }
    }
}
