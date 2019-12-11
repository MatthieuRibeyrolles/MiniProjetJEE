package com.bmt.project.model;

/**
 *
 * @author Thibault
 */
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.derby.jdbc.ClientDataSource;
import org.postgresql.ds.PGSimpleDataSource;

public class DataSourceFactory {

    protected static String DB_NAME = "JavaEEProject";
    protected static String DB_USER = "app";
    protected static String DB_PSWD = "app";
    protected static String DB_HOST = "localhost";
    protected static int DB_PORT = 1527;

    public static DataSource getDataSource() {

        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            DB_USER = dbUri.getUserInfo().split(":")[0];
            DB_PSWD = dbUri.getUserInfo().split(":")[1];
            DB_HOST = dbUri.getHost();
            DB_PORT = dbUri.getPort();
            DB_NAME = dbUri.getPath();

            PGSimpleDataSource ds = new PGSimpleDataSource();

            ds.setDatabaseName(DB_NAME);
            ds.setUser(DB_USER);
            ds.setPassword(DB_PSWD);
            ds.setServerName(DB_HOST);
            ds.setPortNumber(DB_PORT);
            ds.setSsl(true);

            return ds;

        } catch (URISyntaxException ex) {
            Logger.getLogger(DataSourceFactory.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger("DataSourceFactory.class.getName()").log(Level.SEVERE, null, "Connecting to localhost");
        }

        ClientDataSource ds = new ClientDataSource();
        ds.setDatabaseName(DB_NAME);
        ds.setUser(DB_USER);
        ds.setPassword(DB_PSWD);
        ds.setServerName(DB_HOST);
        ds.setPortNumber(DB_PORT);

        return ds;
    }

}
