/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise.ServiceLayer;

import com.sdk.planetpaymentexercise.DataAccessObject.EmployeeDAO;
import com.sdk.planetpaymentexercise.DataAccessObject.FilePersistenceException;
import com.sdk.planetpaymentexercise.DataTransferObject.Employee;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO dao;

    public EmployeeServiceImpl(EmployeeDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Employee> readAllEmployees() throws FilePersistenceException {
        return dao.readEmployeesFromFile();
    }

    @Override
    public List<Employee> sort(List<Employee> employees, int sortSelection) {
        switch (sortSelection) {
            case 1:
                employees = sortAlgorithmFirstName(employees);
                break;
            case 2:
                employees = sortAlgorithmLastName(employees);
                break;
            case 3:
                employees = sortAlgorithmDate(employees);
                break;
        }
        return employees;
    }

    @Override
    public boolean saveAllEmployees(List<Employee> sortedEmployees) {
        return dao.saveEmployeesToFile(sortedEmployees);
    }

    private List<Employee> sortAlgorithmFirstName(List<Employee> employees) {
        Collections.sort(employees, (final Employee object1, final Employee object2) -> object1.getFirstName().compareTo(object2.getFirstName()));
        return employees;
    }

    private List<Employee> sortAlgorithmLastName(List<Employee> employees) {
        Collections.sort(employees, (final Employee object1, final Employee object2) -> object1.getLastName().compareTo(object2.getLastName()));
        return employees;
    }

    private List<Employee> sortAlgorithmDate(List<Employee> employees) {
        Collections.sort(employees, (final Employee object1, final Employee object2) -> object1.getDate().compareTo(object2.getDate()));
        return employees;
    }

}
