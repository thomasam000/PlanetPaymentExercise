/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise.DataAccessObject;

import com.sdk.planetpaymentexercise.DataTransferObject.Employee;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    EmployeeDAO dao = new EmployeeDAOInput1Impl("TestFixedInputFile.txt", "TestOutputFile.txt");

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

    @Test
    public void testReadEmployeesFromFile() throws Exception {
        List<Employee> resultList = dao.readEmployeesFromFile();

        Employee employee = new Employee();
        employee.setFirstName("Jon");
        employee.setLastName("Abner");
        employee.setState("CA");
        employee.setAddress1("1 Jay St");
        employee.setAddress2("Apt 498");
        employee.setCity("Dublin");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        employee.setDate(LocalDate.parse("20031208", formatter));
        employee.setCountry("USA");
        employee.setZipCode("49567");

        //test to see if the employee is succesfully read from the file
        assertTrue(employee.equals(resultList.get(0)));
    }

    @Test
    public void testSaveEmployeesToFile() throws FilePersistenceException {
        boolean expResult = true;
        List<Employee> resultList = dao.readEmployeesFromFile();
        boolean result = dao.saveEmployeesToFile(resultList);
        List<Employee> resultListAfterWriting = dao.readEmployeesFromFile();

        //test if the write method returns true, meaning no errors were encountered
        assertEquals(expResult, result);
        //checks to see if the same list is retrieved after writing that list to the file
        assertEquals(resultList, resultListAfterWriting);
    }

}
