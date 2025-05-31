package ch.challangerson.util;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLUtil {
    private final HikariDataSource hikariDataSource;

    public SQLUtil(String host, int port, String database, String username, String password) {
        this.hikariDataSource = new HikariDataSource();
        this.hikariDataSource.addDataSourceProperty("cachePrepStmts", true);
        this.hikariDataSource.addDataSourceProperty("prepStmtCacheSize", 250);
        this.hikariDataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        this.hikariDataSource.addDataSourceProperty("useServerPrepStmts", true);
        this.hikariDataSource.addDataSourceProperty("rewriteBatchedStatements", true);
        this.hikariDataSource.addDataSourceProperty("useSSL", false);
        this.hikariDataSource.addDataSourceProperty("requireSSL", false);
        this.hikariDataSource.addDataSourceProperty("characterEncoding", "utf8");
        this.hikariDataSource.addDataSourceProperty("encoding", "UTF-8");
        this.hikariDataSource.addDataSourceProperty("useUnicode", true);
        this.hikariDataSource.setJdbcUrl("jdbc:mysql:// " + host + ":" + port + "/" + database);
        this.hikariDataSource.setUsername(username);
        if(password != null && !password.isEmpty()) {
            this.hikariDataSource.setPassword(password);
        }

        this.hikariDataSource.setMaximumPoolSize(5);
        this.hikariDataSource.setConnectionTimeout(30000L);
    }

    public Connection getConnection() {
        try {
            return this.hikariDataSource.getConnection();
        } catch (SQLException ignore) {
            return null;
        }
    }

    public void close() {
        this.hikariDataSource.close();
    }

    public HikariDataSource getHikariDataSource() {
        return this.hikariDataSource;
    }
}
