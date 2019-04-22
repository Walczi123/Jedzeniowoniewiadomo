package SQL;

import Models.Category;
import Models.CategoryGroup;
import SQLTableNames.CategoriesTable;
import SQLTableNames.CategoryGroupsTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created on 20.04.19
 */
public class CategoryGropusSQLProvider {
    private DatabaseProvider datbaseLoader;

    public CategoryGropusSQLProvider(DatabaseProvider databaseLoader) {
        this.datbaseLoader = databaseLoader;
    }

    public CategoryGroup get(Integer id) throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "SELECT " +
                        CategoryGroupsTable.TYPE_ID + "," +
                        CategoryGroupsTable.NAME +
                        " FROM " + CategoryGroupsTable.TABLE_NAME +
                        " WHERE " + CategoryGroupsTable.TYPE_ID + "=?"
        );
        stmt.setInt(1, id);
        var result = stmt.executeQuery();
        if (result.next()) {
            return new CategoryGroup(
                    result.getInt(CategoryGroupsTable.TYPE_ID),
                    result.getString(CategoryGroupsTable.NAME)
            );
        } else {
            return null;
        }
    }

    public HashMap<Integer, CategoryGroup> getAll() throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "SELECT " +
                        CategoryGroupsTable.TYPE_ID + "," +
                        CategoryGroupsTable.NAME +
                        " FROM " + CategoryGroupsTable.TABLE_NAME
        );
        var result = stmt.executeQuery();
        HashMap<Integer, CategoryGroup> ret = new HashMap<>();
        while (result.next()) {
            ret.put(result.getInt(CategoryGroupsTable.TYPE_ID),
                    new CategoryGroup(
                            result.getInt(CategoryGroupsTable.TYPE_ID),
                            result.getString(CategoryGroupsTable.NAME)
                    )
            );
        }
        return ret;
    }

    public CategoryGroup getWithData(Integer id) throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "SELECT " +
                        CategoryGroupsTable.TYPE_ID + "," +
                        CategoryGroupsTable.NAME +
                        " FROM " + CategoryGroupsTable.TABLE_NAME +
                        " WHERE " + CategoryGroupsTable.TYPE_ID + "=?"
        );
        stmt.setInt(1, id);
        var result = stmt.executeQuery();
        if (result.next()) {
            CategoryGroup categoryGroup = new CategoryGroup(
                    result.getInt(CategoryGroupsTable.TYPE_ID),
                    result.getString(CategoryGroupsTable.NAME),
                    new HashMap<>()
            );
            var stmtCategories = c.prepareStatement(
                    "SELECT " +
                            CategoriesTable.TABLE_NAME + "." + CategoriesTable.CATEGORY_ID + "," +
                            CategoriesTable.TABLE_NAME + "." + CategoriesTable.NAME + "," +
                            CategoriesTable.TABLE_NAME + "." + CategoriesTable.TYPE_ID +
                            " FROM " + CategoriesTable.TABLE_NAME +
                            " INNER JOIN " + CategoryGroupsTable.TABLE_NAME + " on " +
                            CategoriesTable.TABLE_NAME + "." + CategoriesTable.TYPE_ID + "=" + CategoryGroupsTable.TABLE_NAME + "." + CategoryGroupsTable.TYPE_ID +
                            " WHERE " + CategoryGroupsTable.TABLE_NAME + "." + CategoryGroupsTable.TYPE_ID + "=?"
            );
            stmtCategories.setInt(1, id);
            result = stmtCategories.executeQuery();
            while (result.next()) {
                categoryGroup.addCategory(
                        new Category(
                                result.getInt(CategoriesTable.CATEGORY_ID),
                                result.getString(CategoriesTable.NAME),
                                result.getInt(CategoriesTable.TYPE_ID)
                        )
                );
            }
            return categoryGroup;
        } else {
            return null;
        }
    }

    public CategoryGroup insert(CategoryGroup categoryGroup) throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "INSERT INTO " + CategoryGroupsTable.TABLE_NAME +
                        "(" + CategoryGroupsTable.NAME + ")" +
                        " VALUES (?)",
                Statement.RETURN_GENERATED_KEYS
        );
        stmt.setString(1, categoryGroup.getName());
        stmt.executeUpdate();

        var result = stmt.getGeneratedKeys();
        if (result.next())
            return new CategoryGroup(
                    result.getInt(CategoryGroupsTable.TYPE_ID),
                    result.getString(CategoryGroupsTable.NAME)
            );
        throw new SQLException("Creating unit failed, no ID obtained.");
    }

    public void delete(Category category) throws SQLException {
        //TODO
    }
}
