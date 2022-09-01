package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataNotFoundException() {
        super();
    }
    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public DataNotFoundException(String message) {
        super(message);
    }
    public DataNotFoundException(Throwable cause) {
        super(cause);
    }
	
	public DataNotFoundException(Long id){
		super("Could not find data for id = " + id);
	}
}
