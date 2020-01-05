/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise.Controllers;

import com.sdk.planetpaymentexercise.DataAccessObject.FilePersistenceException;
import com.sdk.planetpaymentexercise.DataTransferObject.Employee;
import com.sdk.planetpaymentexercise.ServiceLayer.EmployeeService;
import com.sdk.planetpaymentexercise.UI.View;
import java.util.List;

/**
 *
 * @author samuelthomas
 */
public class PlanetPaymentController {

    private View view;
    private EmployeeService service;

    public PlanetPaymentController(View view, EmployeeService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        List<Employee> employees = null;
        int sortSelection = view.sortSelection();
        try {
        employees = service.readAllEmployees();
        } catch (FilePersistenceException e) {
            view.enter(e.getMessage(), "Shutting Down...");
            return;
        }
        view.enter("Sort Records", "Sorting...");
        List<Employee> sortedEmployees = service.sort(employees, sortSelection);
        view.displayEmployees(sortedEmployees);
        view.enter("Save to File", "Saving..."); 
        if (service.saveAllEmployees(sortedEmployees)) {
            view.enter("Complete", "Thank you!");
        }
        

    }

}
