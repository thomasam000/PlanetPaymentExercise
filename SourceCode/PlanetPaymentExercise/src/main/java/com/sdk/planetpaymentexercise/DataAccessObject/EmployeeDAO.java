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

//POLYMORPHISM
//by using an interface the Data Access object (DAO) can take multiple forms
//one DAO can read file format 1 and the other file format 2
//either can be injected and used without effecting the other layers of the program
public interface EmployeeDAO {
    List<Employee> readEmployeesFromFile() throws FilePersistenceException;
    public boolean saveEmployeesToFile(List<Employee> sortedEmployees);

}
