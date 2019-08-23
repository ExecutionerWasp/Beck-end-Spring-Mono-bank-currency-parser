package com.app.exception;

/**
 * @author Alvin
 **/

public class CourseNotFound extends RuntimeException {
    private static final String MESSAGE = "Requested course not found.";

    public CourseNotFound() {
        super(MESSAGE);
    }
}
