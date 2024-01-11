package com.example.projectJava.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException() {
        super("A user with the same email already exists.");
    }

}