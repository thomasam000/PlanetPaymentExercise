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

public class EmployeeDAOInput2Impl implements EmployeeDAO {

    private String DELIMITER = ",";
    private List<Employee> employees = new ArrayList<>();

    private String inputFile;
    private String outputFile;

    public EmployeeDAOInput2Impl(String inputFile, String outputFile) {
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
        Scanner scanner = new Scanner(stream);

        String fileFormat = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            Employee currentEmployee = parseDataIntoEmployee(currentLine);
            employees.add(currentEmployee);
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
        if (!brokenLine[6].equals("")) {
            emp.setState(brokenLine[6]);
        } else {
            emp.setState("CA");
        }
        if (!brokenLine[7].equals("")) {
            emp.setCountry(brokenLine[7]);
        } else {
            emp.setCountry("USA");
        }
        emp.setZipCode(brokenLine[8]);
        return emp;
    }

}
