package com.example.demo.core.restController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.api.dto.FlightDto;
import com.example.demo.core.api.request.FlightFilterRequest;
import com.example.demo.core.api.request.FlightSaveRequest;
import com.example.demo.core.api.response.FlightListResponse;
import com.example.demo.core.api.response.FlightResponse;
import com.example.demo.core.model.FlightModel;
import com.example.demo.core.service.FlightService;
import com.example.demo.integratiom.MapperService;


@RestController
@RequestMapping("/flightApi")
public class FlightRestController {

	private final FlightService flightService;
	private final MapperService mapperService;
	
	FlightRestController(FlightService service, MapperService mapper) {
		this.flightService = service;
		this.mapperService = mapper;
	}

	@GetMapping("/getAllFlightList")
	FlightListResponse getAllFlightList() {
		
		FlightListResponse response = new FlightListResponse();
		String result = "Get all flight successfully.";
		
		List<FlightModel> flightModelList = flightService.getAllFlightList();
		
		response.setFlightList(mapperService.mapList(flightModelList, FlightDto.class));

		response.setResult(result);
		
		return response;
	}
	
	@PostMapping("/saveFligh")
	FlightResponse saveFlight(@RequestBody FlightSaveRequest request) {
		
		FlightResponse response = new FlightResponse();
		String result = "Flight saved successfully.";

		FlightModel flightModel = flightService.saveFlight(request.getNewFlight());
		
		response.setFlight(mapperService.map(flightModel, FlightDto.class));
		
		response.setResult(result);
		
		return response;
	}

	@PostMapping("/findFlight")
	public FlightResponse findFlight(@RequestBody FlightFilterRequest request) {
		
		FlightResponse response = new FlightResponse();
		String result = "Find flight successfully";

		try {

			FlightModel flightModel = flightService.findFlight(request.getFlightId());
			
			response.setFlight(mapperService.map(flightModel, FlightDto.class));
		
		} catch (Exception e) {
			result = e.getMessage();
		}
		
		response.setResult(result);
		
		return response;
	}
	
	@PostMapping("/deleteFlight")
	public FlightResponse deleteFlight(@RequestBody FlightFilterRequest request) {
		
		FlightResponse response = new FlightResponse();
		String result = request.getFlightId() + " ids flight deleted successfully.";

		try {

			flightService.deleteFlight(request.getFlightId());

		} catch (Exception e) {
			result = e.getMessage();
		}
		
		response.setResult(result);
		
		return response;
	}
	
}
