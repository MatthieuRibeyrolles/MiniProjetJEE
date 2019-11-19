/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmt.project.model;

import java.io.File;
import java.io.IOException;
import org.hsqldb.cmdline.SqlFile;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.hsqldb.cmdline.SqlToolError;
import org.hsqldb.jdbc.JDBCDataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

    public DAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws SQLException, IOException, SqlToolError {
        this.myDataSource = DataSourceFactory.getDataSource();
//        this.myConnection = this.myDataSource.getConnection();
//        this.myConnection = DataSourceFactory.getDataSource().getConnection();
//        executeSQLScript(this.myConnection, CREATE_DB);
//        executeSQLScript(this.myConnection, FILL_DB);
        this.myDAO = new DAO(this.myDataSource);
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

}
