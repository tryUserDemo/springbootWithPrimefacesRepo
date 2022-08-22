package com.example.demo.core.api.response;

import com.example.demo.core.api.dto.FlightDto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FlightResponse {

	private String result;

	private FlightDto flight;
	
}
