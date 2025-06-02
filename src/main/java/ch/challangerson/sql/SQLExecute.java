package ch.challangerson.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class SQLExecute {

    private final MySQL databaseAdapter;

    public SQLExecute(String host, int port, String database, String username, String password) {
        this.databaseAdapter = new MySQL(host, port, database, username, password);
    }

    public void executeQuery(String query, Consumer<ResultSet> resultSetConsumer){
        Connection connection = this.databaseAdapter.getConnection();

        try {
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();
            resultSetConsumer.accept(resultSet);

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeUpdateSync(String query) {
        Connection connection = this.databaseAdapter.getConnection();
        connectionExecute(connection, query);
    }

    public void executeUpdateAsync(String query) {
        new Thread(() -> {
            Connection connection = this.databaseAdapter.getConnection();
            connectionExecute(connection, query);
        }).start();
    }


    private void connectionExecute(Connection connection, String query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            if(statement == null) {
                connection.close();
                return;
            }

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MySQL getDatabaseAdapter() {
        return this.databaseAdapter;
    }
}

