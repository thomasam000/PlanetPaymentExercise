/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise.Controllers;

import com.sdk.planetpaymentexercise.DataAccessObject.EmployeeDAOInput1Impl;
import com.sdk.planetpaymentexercise.DataAccessObject.EmployeeDAOInput2Impl;
import com.sdk.planetpaymentexercise.DataAccessObject.FilePersistenceException;
import com.sdk.planetpaymentexercise.DataTransferObject.Employee;
import com.sdk.planetpaymentexercise.ServiceLayer.EmployeeService;
import com.sdk.planetpaymentexercise.ServiceLayer.EmployeeServiceImpl;
import com.sdk.planetpaymentexercise.UI.View;
import java.util.List;

/**
 *
 * @author samuelthomas
 */
public class PlanetPaymentController {

    private View view;
    private EmployeeService service;

    //constructor injection
    public PlanetPaymentController(View view, EmployeeService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        List<Employee> employees = null;
        //choose between sort by first name, last name, and date
        int sortSelection = view.sortSelection();
        
        try {
            //get a list of employees from the the data
            employees = service.readAllEmployees();
        } catch (FilePersistenceException e) {
            //if the program encounters an exception, the program shuts down
            view.enter(e.getMessage(), "Shutting Down...");
            return;
        }
        
        view.enter("Sort Records", "Sorting...");
        //sorts the employees based on the selection made prior
        List<Employee> sortedEmployees = service.sort(employees, sortSelection);
        //shows a preview of the sorted employees
        view.displayEmployees(sortedEmployees);
        view.enter("Save to File", "Saving...");
        //saves the sorted list of employees to the output file
        if (service.saveAllEmployees(sortedEmployees)) {
            view.enter("Complete", "Thank you!");
        }

    }

}
