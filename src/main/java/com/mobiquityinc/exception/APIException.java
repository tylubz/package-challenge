package com.mobiquityinc.exception;

/**
 * Base exception which indicates errors during execution.
 */
public class APIException extends RuntimeException {

    /**
     * Constructor with parameter.
     *
     * @param message the error message
     */
    public APIException(String message) {
        super(message);
    }

}
