/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise;

import com.sdk.planetpaymentexercise.Controllers.PlanetPaymentController;
import com.sdk.planetpaymentexercise.DataAccessObject.EmployeeDAO;
import com.sdk.planetpaymentexercise.DataAccessObject.EmployeeDAOInput1Impl;
import com.sdk.planetpaymentexercise.DataAccessObject.EmployeeDAOInput2Impl;
import com.sdk.planetpaymentexercise.ServiceLayer.EmployeeService;
import com.sdk.planetpaymentexercise.ServiceLayer.EmployeeServiceImpl;
import com.sdk.planetpaymentexercise.UI.View;

/**
 *
 * @author samuelthomas
 */
public class App {

    public static void main(String[] args) {
        //using basic constructor injection instead of spring framework
        //showing what happen behind the scene with spring dependency injection

        //the controller gains access to the view via constructor injection
        View view = new View();
        //
        EmployeeDAO dao1 = new EmployeeDAOInput1Impl("FixedInputFile.txt", "OutputFile.txt");
        EmployeeDAO dao2 = new EmployeeDAOInput2Impl("InputFile.txt", "OutputFile.txt");
        EmployeeService service = null;

        //first i used input file format 2, but i wanted to challenge myself to figure out input file format 1
        //I made it so you could choose between the two files by choosing between the two service layers
        int fileInputSelection = view.fileInputSelection();
        switch (fileInputSelection) {
            case 1:
                service = new EmployeeServiceImpl(dao1);
                break;
            case 2:
                service = new EmployeeServiceImpl(new EmployeeDAOInput2Impl("InputFile.txt", "OutputFile.txt"));
                break;
        }

        PlanetPaymentController controller = new PlanetPaymentController(view, service);

        controller.run();

    }
}
