package com.address.exceptions;

import lombok.Getter;
import lombok.Setter;

public class UnexpectedErrorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String code;
    @Setter
    private String message;

    public UnexpectedErrorException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
