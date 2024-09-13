package edu.sm.exception;

public class DuplicatedEmailException extends Exception {
    public DuplicatedEmailException() {}
    public DuplicatedEmailException(String message) {
        super(message);
    }
}