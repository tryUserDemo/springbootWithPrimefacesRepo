package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public FlightNotFoundException() {
        super();
    }
    public FlightNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public FlightNotFoundException(String message) {
        super(message);
    }
    public FlightNotFoundException(Throwable cause) {
        super(cause);
    }
	
	public FlightNotFoundException(Long id){
		super("Could not find flight " + id);
	}
}
