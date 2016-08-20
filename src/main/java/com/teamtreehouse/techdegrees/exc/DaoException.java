package com.teamtreehouse.techdegrees.exc;

public class DaoException extends Exception {

    private final Exception originalExepction;

    public DaoException(Exception originalException, String message) {
        super(message);
        this.originalExepction = originalException;
    }
}
