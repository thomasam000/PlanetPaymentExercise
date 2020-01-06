/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise.ServiceLayer;

import com.sdk.planetpaymentexercise.DataAccessObject.FilePersistenceException;
import com.sdk.planetpaymentexercise.DataTransferObject.Employee;
import java.util.List;

/**
 *
 * @author samuelthomas
 */
//ABSTRACTION
//the controller has access to the service layer
//the controller is unaware of how the service layer sorts the employees
//but it knows the method signature through the interface 
// - a list of employees and an integer are required as parameters
// - returns a list of employees
public interface EmployeeService {
    
    List<Employee> readAllEmployees() throws FilePersistenceException;
    public List<Employee> sort(List<Employee> employees, int sortSelection);
    public boolean saveAllEmployees(List<Employee> sortedEmployees);
    
}
