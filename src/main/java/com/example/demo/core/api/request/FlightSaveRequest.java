package com.example.demo.core.api.request;

import com.example.demo.core.api.dto.FlightDto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FlightSaveRequest {

	private FlightDto newFlight;
	
}
