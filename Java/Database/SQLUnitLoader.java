package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created on 31.03.19
 */
public final class SQLUnitLoader {
    private DatabaseLoader datbaseLoader;

    public SQLUnitLoader(DatabaseLoader databaseLoader) {
        this.datbaseLoader = databaseLoader;
    }

    public Unit get(Integer id) throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "SELECT unit_id, name FROM units WHERE unit_id=?"
        );
        stmt.setInt(1, id);
        var result = stmt.executeQuery();
        if (result.next()) {
            Unit unit = new Unit(result.getInt("unit_id"), result.getString("name"));
            return unit;
        } else {
            return null;
        }
    }

    public HashMap<Integer, Unit> getAll() throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "SELECT unit_id, name FROM units"
        );
        var result = stmt.executeQuery();
        HashMap<Integer, Unit> ret = new HashMap<>();
        while (result.next()) {
            ret.put(result.getInt("unit_id"), new Unit(result.getInt("unit_id"), result.getString("name")));
        }
        return ret;
    }

    public Unit insert(Unit unit) throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "INSERT INTO units(name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS
        );
        stmt.setString(1, unit.getName());
        stmt.executeUpdate();

        var res = stmt.getGeneratedKeys();
        if(res.next())
            return new Unit(res.getInt(1), unit.getName());
        throw new SQLException("Creating unit failed, no ID obtained.");
    }

    public void delete(Unit unit) throws SQLException {
        Connection c = datbaseLoader.getConnection();
        var stmt = c.prepareStatement(
                "DELETE FROM units WHERE unit_id=? and name=?"
        );
        stmt.setInt(1, unit.getId());
        stmt.setString(2,unit.getName());
        stmt.executeUpdate();
    }
}
