/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise.DataAccessObject;

import com.sdk.planetpaymentexercise.DataTransferObject.Employee;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDAOImpl implements EmployeeDAO {

    private String DELIMITER = ",";
    private List<Employee> employees = new ArrayList<>();

    private String inputFile;
    private String outputFile;

    public EmployeeDAOImpl(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public List<Employee> readEmployeesFromFile() throws FilePersistenceException {
        load();
        return employees;
    }

    @Override
    public boolean saveEmployeesToFile(List<Employee> sortedEmployees) {
        try {
            save(sortedEmployees);
        } catch (FilePersistenceException e) {
            return false;
        }
        return true;
    }

    private void load() throws FilePersistenceException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(inputFile);
        if (stream == null) {
            throw new FilePersistenceException("1stERROR");
        }
        Scanner scanner = null;

        try {
            scanner = new Scanner(stream);
        } catch (Exception e) {
            throw new FilePersistenceException("2ndERROR", e);
        }
        String fileFormat = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            switch (fileFormat) {
                case "1":
                    Employee currentEmployeeFixed = parseDataIntoEmployeeFixed(currentLine);
                    employees.add(currentEmployeeFixed);
                    break;
                case "2":
                    Employee currentEmployee = parseDataIntoEmployee(currentLine);
                    employees.add(currentEmployee);
                    break;
            }
        }
        scanner.close();
    }

    private void save(List<Employee> sortedEmployees) throws FilePersistenceException {
        PrintWriter out;
        String filename = outputFile;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

        try {
            out = new PrintWriter(new FileWriter(filename));
        } catch (IOException e) {
            throw new FilePersistenceException("ERROR", e);
        }
        for (int i = 0; i < sortedEmployees.size(); i++) {
            out.println(i + 1);
            Employee emp = sortedEmployees.get(i);
            out.println(emp.getFirstName() + " " + emp.getLastName() + ", (" + emp.getDate().format(formatter) + ")");
            out.println(emp.getAddress1() + ", " + emp.getAddress2());
            out.println(emp.getCity() + ", " + emp.getState());
            out.println(emp.getCountry() + ", " + emp.getZipCode());
            out.println("");
            out.flush();
        }
        out.close();
        employees.clear();
    }

    private Employee parseDataIntoEmployee(String currentLine) {
        String[] brokenLine = currentLine.split(DELIMITER);
        Employee emp = new Employee();
        emp.setFirstName(brokenLine[0]);
        emp.setLastName(brokenLine[1]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
         emp.setDate(LocalDate.parse(brokenLine[2], formatter));
        } catch (DateTimeParseException ex) {
        emp.setDate(null);
        }
        emp.setAddress1(brokenLine[3]);
        emp.setAddress2(brokenLine[4]);
        emp.setCity(brokenLine[5]);
        emp.setState(brokenLine[6]);
        emp.setCountry(brokenLine[7]);
        emp.setZipCode(brokenLine[8]);
        return emp;
    }

    private Employee parseDataIntoEmployeeFixed(String currentLine) {
        Employee emp = new Employee();
        emp.setFirstName(currentLine.substring(0,10).trim());
        emp.setLastName(currentLine.substring(10,27).trim());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        emp.setDate(LocalDate.parse(currentLine.substring(27,35), formatter));
        emp.setAddress1(currentLine.substring(35,45).trim());
        emp.setAddress2(currentLine.substring(45,55).trim());
        emp.setCity(currentLine.substring(55,65).trim());
        emp.setState(currentLine.substring(65,67).trim());
        emp.setCountry(currentLine.substring(67,70).trim());
        emp.setZipCode(currentLine.substring(70,80).trim());
        return emp;
    }

}
