package com.dev.commons.exceptions;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RestApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	private String message;

    public RestApiException(String message) {
        super(message);
        this.message = message;
    }
}
