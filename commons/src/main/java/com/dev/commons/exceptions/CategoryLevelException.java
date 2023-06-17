package com.dev.commons.exceptions;

import lombok.Getter;

@Getter
public class CategoryLevelException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	private String message;

    public CategoryLevelException(String message) {
        super(message);
        this.message = message;
    }
}
