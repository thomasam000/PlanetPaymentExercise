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

public class EmployeeDAOInput1Impl implements EmployeeDAO {

    private List<Employee> employees = new ArrayList<>();

    private String inputFile;
    private String outputFile;

    public EmployeeDAOInput1Impl(String inputFile, String outputFile) {
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
            out.flush();
        }
        out.close();
        employees.clear();
    }

    private void load() throws FilePersistenceException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(inputFile);
        if (stream == null) {
            throw new FilePersistenceException("File Not Found");
        }
        Scanner scanner = new Scanner(stream);

        String fileFormat = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            Employee currentEmployeeFixed = parseDataIntoEmployeeFixed(currentLine);
            if (currentEmployeeFixed != null) {
                employees.add(currentEmployeeFixed);
            }
        }
        scanner.close();
    }

    //to parse the data with white space I broke the string into substrings and trimmed off the extra white space at the end
    private Employee parseDataIntoEmployeeFixed(String currentLine) throws FilePersistenceException {
        Employee emp = new Employee();
        try {
            emp.setFirstName(currentLine.substring(0, 10).trim());
            emp.setLastName(currentLine.substring(10, 27).trim());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            //defensive programming
            //preventing possible exception when parsing date 
            try {
                emp.setDate(LocalDate.parse(currentLine.substring(27, 35), formatter));
            } catch (DateTimeParseException ex) {
                emp.setDate(null);
            }
        //if employee record does not contain first name last name or date the record is invalid
            if (emp.getFirstName().equals("") || emp.getLastName().equals("") || emp.getDate() == null) {
                return null;
            }
            emp.setAddress1(currentLine.substring(35, 45).trim());
            emp.setAddress2(currentLine.substring(45, 55).trim());
            emp.setCity(currentLine.substring(55, 65).trim());
            //state and country have default values if it was left blank
            if (!currentLine.substring(65, 67).trim().equals("")) {
                emp.setState(currentLine.substring(65, 67).trim());
            } else {
                emp.setState("CA");
            }
            if (!currentLine.substring(67, 70).trim().equals("")) {
                emp.setCountry(currentLine.substring(67, 70).trim());
            } else {
                emp.setCountry("USA");
            }
            emp.setZipCode(currentLine.substring(70, 80).trim());
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
        return emp;
    }

}
