/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdk.planetpaymentexercise.DataAccessObject;

/**
 * @author samuelthomas
 */
//INHERITANCE
//this classs inherits capabilities from the parent class Exception through inheritance
//custom exception class that I can through and catch whenever I encounter problems with file persistence

public class FilePersistenceException extends Exception {

    public FilePersistenceException(String message) {
        super(message);
    }

    public FilePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}