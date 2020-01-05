/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise.DataAccessObject;

import com.sdk.planetpaymentexercise.DataTransferObject.Employee;
import java.util.List;

/**
 *
 * @author samuelthomas
 */
public interface EmployeeDAO {
    List<Employee> readEmployeesFromFile() throws FilePersistenceException;
    public boolean saveEmployeesToFile(List<Employee> sortedEmployees);

}
