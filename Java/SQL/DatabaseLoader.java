package SQL;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DatabaseLoader {
    protected String server;
    protected String login;
    protected String password;
    protected Connection connection;

    public DatabaseLoader(String server, String login, String password) {
        this.server = server;
        this.login = login;
        this.password = password;
    }

    public abstract Connection open() throws ClassNotFoundException, SQLException;

    public abstract void close();

    public Connection getConnection() {
        return this.connection;
    }
}
