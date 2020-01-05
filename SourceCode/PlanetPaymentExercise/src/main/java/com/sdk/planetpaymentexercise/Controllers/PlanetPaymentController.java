/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise.Controllers;

import com.sdk.planetpaymentexercise.DataAccessObject.EmployeeDAO;
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
    private EmployeeDAO dao;
    EmployeeService service;

    public PlanetPaymentController(View view) {
        this.view = view;
    }

    public void run() {
        int fileInputSelection = view.fileInputSelection();
        switch (fileInputSelection) {
            case 1:
                dao = new EmployeeDAOInput1Impl("FixedInputFile.txt", "OutputFile.txt");
                break;
            case 2:
                dao = new EmployeeDAOInput2Impl("InputFile.txt", "OutputFile.txt");
                break;
        }
        service = new EmployeeServiceImpl(dao);

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
