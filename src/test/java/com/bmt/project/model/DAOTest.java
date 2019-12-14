package com.bmt.project.model;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.sql.DataSource;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.hsqldb.jdbc.JDBCDataSource;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dalfrak
 */
public class DAOTest {

    protected final String CREATE_DB = "ComptoirDerby_Schema.sql";
    protected final String FILL_DB = "ComptoirInnoDB_Data.sql";

    private DataSource myDataSource;
    private Connection myConnection;
    private DAO myDAO;
    private List<ClientEntity> clientsList;
    private ClientEntity testClient;

    public DAOTest() {
    }

    private void executeSQLScript(Connection myConnection, String filename) throws IOException, SqlToolError, SQLException {
        String sqlFilePath = DAOTest.class.getResource(filename).getFile();
        SqlFile sqlFile = new SqlFile(new File(sqlFilePath));

        sqlFile.setConnection(myConnection);
        sqlFile.execute();
        sqlFile.closeReader();
    }

    public static DataSource getDataSource() {
        JDBCDataSource ds = new JDBCDataSource();
        ds.setDatabase("jdbc:hsqldb:mem:testcase;shutdown=true");
        ds.setUser("sa");
        ds.setPassword("sa");
        return ds;
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException, IOException, SqlToolError {
        this.myDataSource = getDataSource();
        this.myConnection = this.myDataSource.getConnection();
        executeSQLScript(this.myConnection, CREATE_DB);
        executeSQLScript(this.myConnection, FILL_DB);
        this.myDAO = new DAO(this.myDataSource);
        this.testClient = new ClientEntity("ALFKI", "Alfreds Futterkiste", "Maria Anders", "Représentant(e)", "Obere Str. 57", "Berlin", null, "12209", "Allemagne", "030-0074321", "030-0076545");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getClientsList method, of class DAO.
     */
    @Test
    public void testGetClientsList() {
        System.out.println("getClientsList");
        DAO instance = this.myDAO;
        int expResult = 91;
        int result = instance.getClientsList().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrdersList method, of class DAO.
     */
    @Test
    public void testGetOrdersList() {
        System.out.println("getOrdersList");
        DAO instance = this.myDAO;
        int expResult = 830;
        int result = instance.getOrdersList().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCategoriesList method, of class DAO.
     */
    @Test
    public void testGetCategoriesList() {
        System.out.println("getCategoriesList");
        DAO instance = this.myDAO;
        int expResult = 8;
        int result = instance.getCategoriesList().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of login method, of class DAO.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String log = "Maria Anders";
        String pass = "ALFKI";
        DAO instance = this.myDAO;
        ClientEntity expResult = this.testClient;
        ClientEntity result = instance.login(log, pass);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientBycode method, of class DAO.
     */
    @Test
    public void testGetClientBycode() {
        System.out.println("getClientBycode");
        String codeC = "ALFKI";
        DAO instance = this.myDAO;
        instance.getClientsList();
        ClientEntity expResult = this.testClient;
        ClientEntity result = instance.getClientBycode(codeC);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateClient method, of class DAO.
     */
    @Test
    public void testUpdateClient() {
        System.out.println("updateClient");
        ClientEntity oldC = this.testClient;
        ClientEntity newC = new ClientEntity("ALFKI", "Alfreds Futterkiste", "chuck Norris", "Représentant(e)", "Obere Str. 57", "Berlin", null, "12209", "Allemagne", "030-0074321", "030-0076545");;
        DAO instance = this.myDAO;
        boolean expResult = true;
        boolean result = instance.updateClient(oldC, newC);
        assertEquals(expResult, result);
    }

    /**
     * Test of addClient method, of class DAO.
     */
    @Test
    public void testAddClient() {
        System.out.println("addClient");
        ClientEntity newC = new ClientEntity("ZZZZZ", "dhtoetozeqgyu", "chuck Norris", "Représentant(e)", "Obere Str. 57", "Berlin", null, "12209", "Allemagne", "030-0074321", "030-0076545");;;
        DAO instance = this.myDAO;
        ClientEntity expResult = newC;
        ClientEntity result = instance.addClient(newC);
        assertEquals(expResult, result);
    }

    /**
     * Test of addOrder method, of class DAO.
     */
    @Test
    public void testAddOrder() {
        System.out.println("addOrder");
        DAO instance = this.myDAO;
        instance.getClientsList();
        OrderEntity newO = new OrderEntity(instance.getClientBycode("ALFKI"), Date.valueOf(LocalDate.now()), 69.0f, "Rattlesnake Canyon Grocery", "2817 Milton Dr.", "Albuquerque", "NM", "87110", "Etats-Unis", 0.00f);
        int expResult = 1;
        int result = instance.addOrder(newO).getNum();
        assertEquals(expResult, result);
    }

    /**
     * Test of getProductsList method, of class DAO.
     */
    @Test
    public void testGetProductsList() {
        System.out.println("getProductsList");
        DAO instance = this.myDAO;
        int expResult = 77;
        int  result = instance.getProductsList().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCategoryByCode method, of class DAO.
     */
    @Test
    public void testGetCategoryByCode() {
        System.out.println("getCategoryByCode");
        int code = 1;
        DAO instance = this.myDAO;
        instance.getCategoriesList();
        CategoryEntity expResult = new CategoryEntity(1, "Boissons", "Boissons, cafés, thés, bières");
        CategoryEntity result = instance.getCategoryByCode(code);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrderListByClient method, of class DAO.
     */
    @Test
    public void testGetOrderListByClient() {
        System.out.println("getOrderListByClient");
        String codeC = "ALFKI";
        DAO instance = this.myDAO;
        int expResult = 4;
        int result = instance.getOrderListByClient(codeC).size();
        assertEquals(expResult, result);
    }

}
