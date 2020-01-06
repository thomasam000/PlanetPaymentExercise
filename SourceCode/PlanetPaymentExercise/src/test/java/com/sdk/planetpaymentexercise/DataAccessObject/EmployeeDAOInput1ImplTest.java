/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise.DataAccessObject;

import com.sdk.planetpaymentexercise.DataTransferObject.Employee;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samuelthomas
 */
public class EmployeeDAOInput1ImplTest {
    
    public EmployeeDAOInput1ImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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

    //@Test
    public void testReadEmployeesFromFile() throws Exception {
        System.out.println("readEmployeesFromFile");
        EmployeeDAOInput1Impl instance = null;
        List<Employee> expResult = null;
        List<Employee> result = instance.readEmployeesFromFile();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    //@Test
    public void testSaveEmployeesToFile() {
        System.out.println("saveEmployeesToFile");
        List<Employee> sortedEmployees = null;
        EmployeeDAOInput1Impl instance = null;
        boolean expResult = false;
        boolean result = instance.saveEmployeesToFile(sortedEmployees);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
