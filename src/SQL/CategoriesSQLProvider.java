package SQL;

import Models.Category;
import Models.Unit;
import SQLTableNames.CategoriesTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created on 20.04.19
 */
public class CategoriesSQLProvider {
    private DatabaseProvider datbaseLoader;

    public CategoriesSQLProvider(DatabaseProvider databaseLoader) {
        this.datbaseLoader = databaseLoader;
    }

    public Category get(Integer id) throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "SELECT " +
                        CategoriesTable.CATEGORY_ID + "," +
                        CategoriesTable.NAME + "," +
                        CategoriesTable.TYPE_ID +
                        " FROM " + CategoriesTable.TABLE_NAME +
                        " WHERE " + CategoriesTable.CATEGORY_ID + "=?"
        );
        stmt.setInt(1, id);
        var result = stmt.executeQuery();
        if (result.next()) {
            return new Category(
                    result.getInt(CategoriesTable.CATEGORY_ID),
                    result.getString(CategoriesTable.NAME),
                    result.getInt(CategoriesTable.TYPE_ID)
            );
        } else {
            return null;
        }
    }

    public HashMap<Integer, Category> getAll() throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "SELECT " +
                        CategoriesTable.CATEGORY_ID + "," +
                        CategoriesTable.NAME + "," +
                        CategoriesTable.TYPE_ID +
                        " FROM " + CategoriesTable.TABLE_NAME
        );
        var result = stmt.executeQuery();
        HashMap<Integer, Category> ret = new HashMap<>();
        while (result.next()) {
            ret.put(result.getInt(CategoriesTable.CATEGORY_ID),
                    new Category(
                            result.getInt(CategoriesTable.CATEGORY_ID),
                            result.getString(CategoriesTable.NAME),
                            result.getInt(CategoriesTable.TYPE_ID)
                    )
            );
        }
        return ret;
    }

    public Category insert(Category category) throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "INSERT INTO " + CategoriesTable.TABLE_NAME +
                        "(" + CategoriesTable.NAME + "," + CategoriesTable.TYPE_ID + ")" +
                        " VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS
        );
        stmt.setString(1, category.getName());
        stmt.setInt(2, category.getTypeId());
        stmt.executeUpdate();

        var result = stmt.getGeneratedKeys();
        if (result.next())
            return new Category(
                result.getInt(CategoriesTable.CATEGORY_ID),
                result.getString(CategoriesTable.NAME),
                result.getInt(CategoriesTable.TYPE_ID)
        );
        throw new SQLException("Creating unit failed, no ID obtained.");
    }

    public void delete(Category category) throws SQLException {
        //TODO
    }
}
