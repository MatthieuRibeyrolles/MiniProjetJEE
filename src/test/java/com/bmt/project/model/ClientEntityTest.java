/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bmt.project.model;

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
public class ClientEntityTest {
    
    private static ClientEntity testClient;
    
    public ClientEntityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        testClient = new ClientEntity("AAAA", "Yolo", "Chuck Norris", "Maitre du monde", "6 rue de la boustifaille", "GrosVille", "Celle du cassoulet", "42666", "GrosLand", "0642424242", null);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCode method, of class ClientEntity.
     */
    @Test
    public void testGetCode() {
        System.out.println("getCode");
        ClientEntity instance = this.testClient;
        String expResult = "AAAA";
        String result = instance.getCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCode method, of class ClientEntity.
     */
    @Test
    public void testSetCode() {
        System.out.println("setCode");
        String code = "";
        ClientEntity instance = this.testClient;
        instance.setCode(code);
    }

    /**
     * Test of getCompany method, of class ClientEntity.
     */
    @Test
    public void testGetCompany() {
        System.out.println("getCompany");
        ClientEntity instance = null;
        String expResult = "";
        String result = instance.getCompany();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCompany method, of class ClientEntity.
     */
    @Test
    public void testSetCompany() {
        System.out.println("setCompany");
        String company = "Company";
        ClientEntity instance = this.testClient;
        instance.setCompany(company);
    }

    /**
     * Test of getContact method, of class ClientEntity.
     */
    @Test
    public void testGetContact() {
        System.out.println("getContact");
        ClientEntity instance = null;
        String expResult = "";
        String result = instance.getContact();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContact method, of class ClientEntity.
     */
    @Test
    public void testSetContact() {
        System.out.println("setContact");
        String contact = "Contact";
        ClientEntity instance = this.testClient;
        instance.setContact(contact);
    }

    /**
     * Test of getRole method, of class ClientEntity.
     */
    @Test
    public void testGetRole() {
        System.out.println("getRole");
        ClientEntity instance = null;
        String expResult = "";
        String result = instance.getRole();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRole method, of class ClientEntity.
     */
    @Test
    public void testSetRole() {
        System.out.println("setRole");
        String role = "";
        ClientEntity instance = null;
        instance.setRole(role);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddress method, of class ClientEntity.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        ClientEntity instance = this.testClient;
        String expResult = "6 rue de la boustifaille";
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class ClientEntity.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "";
        ClientEntity instance = null;
        instance.setAddress(address);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCity method, of class ClientEntity.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        ClientEntity instance = null;
        String expResult = "";
        String result = instance.getCity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCity method, of class ClientEntity.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        String city = "";
        ClientEntity instance = null;
        instance.setCity(city);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRegion method, of class ClientEntity.
     */
    @Test
    public void testGetRegion() {
        System.out.println("getRegion");
        ClientEntity instance = null;
        String expResult = "";
        String result = instance.getRegion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRegion method, of class ClientEntity.
     */
    @Test
    public void testSetRegion() {
        System.out.println("setRegion");
        String region = "";
        ClientEntity instance = null;
        instance.setRegion(region);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZipCode method, of class ClientEntity.
     */
    @Test
    public void testGetZipCode() {
        System.out.println("getZipCode");
        ClientEntity instance = null;
        String expResult = "";
        String result = instance.getZipCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setZipCode method, of class ClientEntity.
     */
    @Test
    public void testSetZipCode() {
        System.out.println("setZipCode");
        String zipCode = "";
        ClientEntity instance = null;
        instance.setZipCode(zipCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountry method, of class ClientEntity.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        ClientEntity instance = null;
        String expResult = "";
        String result = instance.getCountry();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCountry method, of class ClientEntity.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        String country = "country";
        ClientEntity instance = this.testClient;
        instance.setCountry(country);
    }

    /**
     * Test of getPhone method, of class ClientEntity.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        ClientEntity instance = this.testClient;
        String expResult = "0642424242";
        String result = instance.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPhone method, of class ClientEntity.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String phone = "";
        ClientEntity instance = null;
        instance.setPhone(phone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFax method, of class ClientEntity.
     */
    @Test
    public void testGetFax() {
        System.out.println("getFax");
        ClientEntity instance = null;
        String expResult = "";
        String result = instance.getFax();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFax method, of class ClientEntity.
     */
    @Test
    public void testSetFax() {
        System.out.println("setFax");
        String fax = "";
        ClientEntity instance = null;
        instance.setFax(fax);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ClientEntity.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ClientEntity instance = this.testClient;
        String expResult = "ClientEntity{code=AAAA, company=Company, contact=Contact, role=Maitre du monde, address=6 rue de la boustifaille, city=GrosVille, region=Celle du cassoulet, zipCode=42666, country=country, phone=0642424242, fax=null}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ClientEntity.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ClientEntity instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class ClientEntity.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        ClientEntity instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
