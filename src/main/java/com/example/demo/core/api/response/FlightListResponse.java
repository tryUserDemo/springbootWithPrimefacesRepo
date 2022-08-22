package com.example.demo.core.api.response;

import java.util.List;

import com.example.demo.core.api.dto.FlightDto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FlightListResponse {

	private String result;

	private List<FlightDto> flightList;
	
}
