package SQL;

import Models.Price;
import SQLTablesNames.*;

import java.sql.Connection;
import java.sql.SQLException;

public class PriceSQLProvider {
    private DatabaseProvider datbaseProvider;

    public  PriceSQLProvider(DatabaseProvider databaseProvider) { this.datbaseProvider=databaseProvider; }

    public Price getPrice(int priceId) throws SQLException {
        //DESCRIPTION: return product of given id (productId)
        Price result=new Price();
        Connection c=datbaseProvider.getConnection();

        //Query
        var statement=c.prepareStatement("SELECT * FROM "+ PriceTable.TABLE_NAME+" WHERE "+PriceTable.PRICE_ID+"=?");
        statement.setInt(1, priceId);
        //Execute Queries
        var result_record=statement.executeQuery();
        //Save data
        result_record.next();
        result.setValue(result_record.getFloat(PriceTable.VALUE));
        result.setProductId(result_record.getInt(PriceTable.PRODUCT_ID));
        result.setDateString(result_record.getString(PriceTable.DATE));
        return result;
    }

    public void insertPrice(Price price) throws SQLException {
        //DESCRIPTION: add price to the price table
        Connection c=datbaseProvider.getConnection();
        var statement=c.prepareStatement("INSERT INTO "+PriceTable.TABLE_NAME+" ("+PriceTable.VALUE+","+PriceTable.PRODUCT_ID+","+PriceTable.DATE+") VALUES (?,?,?)");
        statement.setDouble(1, price.getValue());
        statement.setInt(2, price.getProductId());
        statement.setString(3, price.getDate());
        statement.executeUpdate();
    }

    public void updatePrice(int priceId, Price price)throws SQLException{
        //connecting
        Connection connection=datbaseProvider.getConnection();
        //Query 1
        var statement=connection.prepareStatement("UPDATE "+PriceTable.TABLE_NAME+" SET "+PriceTable.VALUE+"=?,"+PriceTable.PRODUCT_ID+"=?,"+PriceTable.DATE+"=? WHERE price_id=?");
        statement.setDouble(1,price.getValue());
        statement.setInt(2,price.getProductId());
        statement.setString(3,price.getDate());
        statement.setInt(4,priceId);

        //execute the update statement
        statement.executeUpdate();
    }
}



