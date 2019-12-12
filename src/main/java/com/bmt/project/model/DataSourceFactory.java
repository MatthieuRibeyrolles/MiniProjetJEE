package com.bmt.project.model;

/**
 *
 * @author Thibault
 */
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.apache.derby.jdbc.ClientDataSource;
import org.hsqldb.jdbc.JDBCDataSource;
import org.postgresql.ds.PGSimpleDataSource;

public class DataSourceFactory {

    protected static String DB_NAME = "JavaEEProject";
    protected static String DB_USER = "app";
    protected static String DB_PSWD = "app";
    protected static String DB_HOST = "localhost";
    protected static int DB_PORT = 1527;

    public static DataSource getDataSource() {

        try {
            String sEnv = System.getenv("DATABASE_URL");
            if (sEnv != null) {
                URI dbUri = new URI(sEnv);

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
                
                System.out.println(DB_USER);
                System.out.println(DB_PSWD);


                return ds;
            }

        } catch (URISyntaxException ex) {
            Logger.getLogger(DataSourceFactory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger("DataSourceFactory.class.getName()").log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        Logger.getLogger("DataSourceFactory.class.getName()").log(Level.INFO, "Connecting to localhost");

        ClientDataSource ds = new ClientDataSource();
        ds.setDatabaseName(DB_NAME);
        ds.setUser(DB_USER);
        ds.setPassword(DB_PSWD);
        ds.setServerName(DB_HOST);
        ds.setPortNumber(DB_PORT);
        
        return ds;
    }

}
