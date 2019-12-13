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
                DB_NAME = dbUri.getPath().split("/")[1];

                PGSimpleDataSource ds = new PGSimpleDataSource();

                String dbUrl = System.getenv("DATABASE_URL");

                ds.setDatabaseName(DB_NAME);
                ds.setUser(DB_USER);
                ds.setPassword(DB_PSWD);
                ds.setServerName(DB_HOST);
                ds.setPortNumber(DB_PORT);
                ds.setSsl(true);
                ds.setSslfactory("org.postgresql.ssl.NonValidatingFactory");

                Logger.getLogger("DataSourceFactory.class.getName()").log(Level.INFO, "Connecting to provided database");

                return ds;

            } else
                Logger.getLogger("DataSourceFactory.class.getName()").log(Level.WARNING, "La variable d'environnement \"DATABASE_URL\" n'existe pas !");

        } catch (URISyntaxException ex) {
            Logger.getLogger(DataSourceFactory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        PGSimpleDataSource ds = new PGSimpleDataSource();

        ds.setDatabaseName(DB_NAME);
        ds.setUser(DB_USER);
        ds.setPassword(DB_PSWD);
        ds.setServerName(DB_HOST);
        ds.setPortNumber(DB_PORT);
        
        Logger.getLogger("DataSourceFactory.class.getName()").log(Level.INFO, "Connecting to localhost");

        return ds;
    }

}
