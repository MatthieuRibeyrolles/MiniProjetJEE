package com.bmt.project.model;

/**
 *
 * @author Thibault
 */
import javax.sql.DataSource;
import org.apache.derby.jdbc.ClientDataSource;

public class DataSourceFactory {

    protected static final String DB_NAME = "JavaEEProject";
    protected static final String DB_USER = "app";
    protected static final String DB_PSWD = "app";
    protected static final String SV_NAME = "localhost";
    protected static final int SV_PORT = 1527;

    public static DataSource getDataSource() {
        ClientDataSource ds = new ClientDataSource();
        ds.setDatabaseName(DB_NAME);
        ds.setUser(DB_USER);
        ds.setPassword(DB_PSWD);
        ds.setServerName(SV_NAME);
        ds.setPortNumber(SV_PORT);
        return ds;
    }

}
