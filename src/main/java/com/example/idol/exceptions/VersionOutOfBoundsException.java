package com.example.idol.exceptions;

/**
 * VersionOutOfBoundsException, thrown when the previous or next version is requested and is not available.
 */
public class VersionOutOfBoundsException extends Exception {
    public VersionOutOfBoundsException() {
        super("Version out of bounds!");
    }

    public VersionOutOfBoundsException(String errorMessage) {
        super(errorMessage);
    }

    public VersionOutOfBoundsException(Throwable err) {
        super("Version out of bounds!", err);
    }

    public VersionOutOfBoundsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
