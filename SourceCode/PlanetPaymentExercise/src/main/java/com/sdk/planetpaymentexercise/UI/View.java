/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise.UI;

import com.sdk.planetpaymentexercise.DataTransferObject.Employee;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author samuelthomas
 */
public class View {

    public int sortSelection() {
        return promptInt("1. sort by first name \n2. sort by last name \n3. sort by date", 1, 3);
    }


    public void enter(String msg1, String msg2) {
        display(msg1 + "\n - press enter to continue - ");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        display(msg2 + "\n");
    }

    private void display(String msg) {
        System.out.println(msg);
    }

    public int promptInt(String msg, int min, int max) {
        int answer = 0;
        boolean valid = false;
        while (valid == false) {
            Scanner sc = new Scanner(System.in);
            display(msg);
            String str = sc.nextLine();
            try {
                answer = Integer.parseInt(str);
                if (answer <= max && answer >= min) {
                valid = true;
                } else {
                display("ENTER A NUMBER BETWEEN " + min + " AND " + max );
                }
            } catch (NumberFormatException e) {
                display("NOT A NUMBER");
            }
        }
        return answer;
    }

    public void displayEmployees(List<Employee> employees) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        for (Employee emp : employees) {
            display(emp.getFirstName() + " " + emp.getLastName() + ", (" + emp.getDate().format(formatter) + ")");
            display(emp.getAddress1() + ", " + emp.getAddress2());
            display(emp.getCity() + ", " + emp.getState());
            display(emp.getCountry() + ", " + emp.getZipCode());
            display("");
        }
    }

    public int fileInputSelection() {
        return promptInt("1. File Format 1 \n2. File Format 2", 1, 2);
    }
}
