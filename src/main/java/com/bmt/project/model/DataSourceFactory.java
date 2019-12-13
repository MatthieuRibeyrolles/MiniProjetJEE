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
            String sEnv = System.getenv("DATABASE_URL");
            Logger.getLogger("DataSourceFactory.class.getName()").log(Level.INFO, "sEnv: " + sEnv);
            if (sEnv != null) {

                URI dbUri = new URI(sEnv);

                DB_USER = dbUri.getUserInfo().split(":")[0];
                DB_PSWD = dbUri.getUserInfo().split(":")[1];
                DB_HOST = dbUri.getHost();
                DB_PORT = dbUri.getPort();
                DB_NAME = dbUri.getPath().split("/")[1];

                PGSimpleDataSource ds = new PGSimpleDataSource();

                ds.setDatabaseName(DB_NAME);
                ds.setUser(DB_USER);
                ds.setPassword(DB_PSWD);
                ds.setServerName(DB_HOST);
                ds.setPortNumber(DB_PORT);
                ds.setSsl(true);

                Logger.getLogger("DataSourceFactory.class.getName()").log(Level.INFO, "Connecting to provided database");

                Logger.getLogger("DataSourceFactory.class.getName()").log(Level.INFO,
                        "Connecting to : {0} | login : {1} | p : {2} | svName : {3} | port : {4}",
                        new Object[]{DB_NAME, DB_USER, DB_PSWD, DB_HOST, DB_PORT}
                );

                return ds;
            } else
                Logger.getLogger("DataSourceFactory.class.getName()").log(Level.WARNING, "La variable d'environnement \"DATABASE_URL\" n'existe pas !");

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
