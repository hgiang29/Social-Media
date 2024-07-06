package com.social.socialapi.exceptions;

public class FuncErrorException extends RuntimeException{
    private static final long serialVersionUID = 5003320033602480096L;
    public FuncErrorException(final String message) {
        super(message);
    }
}
