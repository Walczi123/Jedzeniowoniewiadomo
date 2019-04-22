package SQL;

import Models.Unit;
import SQLTableNames.UnitsTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created on 31.03.19
 */
public final class UnitSQLProvider {
    private DatabaseProvider datbaseLoader;

    public UnitSQLProvider(DatabaseProvider databaseLoader) {
        this.datbaseLoader = databaseLoader;
    }

    public Unit get(Integer id) throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "SELECT " + UnitsTable.UNIT_ID + ", " +
                        UnitsTable.NAME +
                        " FROM " + UnitsTable.TABLE_NAME +
                        " WHERE " + UnitsTable.UNIT_ID + "=?"
        );
        stmt.setInt(1, id);
        var result = stmt.executeQuery();
        if (result.next()) {
            return new Unit(
                    result.getInt(UnitsTable.UNIT_ID),
                    result.getString(UnitsTable.NAME)
            );
        } else {
            return null;
        }
    }

    public HashMap<Integer, Unit> getAll() throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "SELECT " + UnitsTable.NAME + ", " + UnitsTable.UNIT_ID + " FROM " + UnitsTable.TABLE_NAME
        );
        var result = stmt.executeQuery();
        HashMap<Integer, Unit> ret = new HashMap<>();
        while (result.next()) {
            ret.put(result.getInt(UnitsTable.UNIT_ID), new Unit(result.getInt(UnitsTable.UNIT_ID), result.getString(UnitsTable.NAME)));
        }
        return ret;
    }

    public Unit insert(Unit unit) throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "INSERT INTO " + UnitsTable.TABLE_NAME + "(" + UnitsTable.NAME + ") VALUES (?)",
                Statement.RETURN_GENERATED_KEYS
        );
        stmt.setString(1, unit.getName());
        stmt.executeUpdate();

        var res = stmt.getGeneratedKeys();
        if (res.next())
            return new Unit(res.getInt(1), unit.getName());
        throw new SQLException("Creating unit failed, no ID obtained.");
    }

    public void delete(Unit unit) throws SQLException {
        //TODO
    }
}
