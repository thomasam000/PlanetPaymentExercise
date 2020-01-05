/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise.ServiceLayer;

import com.sdk.planetpaymentexercise.DataAccessObject.EmployeeDAO;
import com.sdk.planetpaymentexercise.DataAccessObject.EmployeeDAOImpl;
import com.sdk.planetpaymentexercise.DataAccessObject.FilePersistenceException;
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
public class EmployeeServiceImplTest {

    List<Employee> employees;
    EmployeeDAO dao = new EmployeeDAOImpl("TestInputFile.txt", "TestOutputFile.txt");
    EmployeeService service = new EmployeeServiceImpl(dao);

    public EmployeeServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws FilePersistenceException {
        employees = dao.readEmployeesFromFile();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSortFirstName() {
        List<Employee> sorted = service.sort(employees, 1);
        String exp1 = "Angela";
        String exp2 = "Jon";
        String exp3 = "Sam";
        assertEquals(exp1, sorted.get(0).getFirstName());
        assertEquals(exp2, sorted.get(1).getFirstName());
        assertEquals(exp3, sorted.get(2).getFirstName());
    }

    @Test
    public void testSortLastName() {
        List<Employee> sorted = service.sort(employees, 2);
        String exp1 = "Jon";
        String exp2 = "Angela";
        String exp3 = "Sam";
        assertEquals(exp1, sorted.get(0).getFirstName());
        assertEquals(exp2, sorted.get(1).getFirstName());
        assertEquals(exp3, sorted.get(2).getFirstName());
    }

    @Test
    public void testSortDate() {
        List<Employee> sorted = service.sort(employees, 3);
        String exp1 = "Sam";
        String exp2 = "Jon";
        String exp3 = "Angela";
        assertEquals(exp1, sorted.get(0).getFirstName());
        assertEquals(exp2, sorted.get(1).getFirstName());
        assertEquals(exp3, sorted.get(2).getFirstName());

    }

}
