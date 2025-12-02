package com.wipro.candidate.exception;

public class WrongDataException extends Exception {

    private String message;

    // Default constructor
    public WrongDataException() {
        this.message = "Wrong data entered!";
    }

    // Parameterized constructor
    public WrongDataException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WrongDataException: " + message;
    }
}


 