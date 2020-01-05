/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise;

import com.sdk.planetpaymentexercise.Controllers.PlanetPaymentController;
import com.sdk.planetpaymentexercise.DataAccessObject.EmployeeDAO;
import com.sdk.planetpaymentexercise.DataAccessObject.EmployeeDAOImpl;
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
    //keeping it loosely coupled
    
    View view = new View();
    EmployeeDAO dao = new EmployeeDAOImpl("FixedInputFile.txt", "OutputFile.txt");
    EmployeeService service = new EmployeeServiceImpl(dao);
    PlanetPaymentController controller = new PlanetPaymentController(view,service);
    
    controller.run();
    
    }
}
