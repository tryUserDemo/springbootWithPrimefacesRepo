package com.example.demo.core.restController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.FlightNotFoundException;
import com.example.demo.core.api.FlightDto;
import com.example.demo.core.api.FlightFilterDto;
import com.example.demo.core.api.FlightResponse;
import com.example.demo.core.model.FlightModel;
import com.example.demo.core.service.FlightService;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.repository.FlightRepository;
import com.example.demo.integratiom.MapperService;


@RestController
@RequestMapping("/flightApi")
public class FlightRestController {

	private final FlightRepository flightRepository;
	private final FlightService flightService;
	private final MapperService mapperService;
	
	FlightRestController(FlightRepository repo, FlightService service, MapperService mapper) {
		this.flightRepository = repo;
		this.flightService = service;
		this.mapperService = mapper;
	}


	@GetMapping("/getAllFlightList")
	List<Flight> getAllFlightList() {
		return flightRepository.findAllByOrderByIdDesc();
	}

	@GetMapping("/getFlightById/{searchId}")
	Flight getFlightById(@PathVariable(value = "searchId") Long flightId) {
		return flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException(flightId));
	}

	@PostMapping("/saveFlight")
	Flight saveFlight(@RequestBody Flight newFlight) {
		return flightRepository.save(newFlight);
	}

	@DeleteMapping("/deleteFlight/{id}")
	void deleteFlight(@PathVariable(value = "id") Long flightId) {
		flightRepository.deleteById(flightId);
	}

	@PostMapping("/findFlight")
	public FlightResponse findFlight(@RequestBody FlightFilterDto filter) {
		
		FlightResponse response = new FlightResponse();
		String result = "Find flight successfully";
		Flight flight = null;
		
		try {
			
			flight = flightRepository.findById(filter.getFlightId())
					.orElseThrow(() -> new FlightNotFoundException(filter.getFlightId()));
			
			FlightDto dto = new FlightDto();
			dto.setId(flight.getId());
			dto.setFromAirport(flight.getFromAirport());
			dto.setToAirport(flight.getToAirport());
			dto.setAirline(flight.getAirline());
			dto.setActualDateTime(flight.getActualDateTime());
			dto.setEstimatedDateTime(flight.getEstimatedDateTime());
			dto.setScheduledDateTime(flight.getScheduledDateTime());
			
			response.setFlightDto(dto);
		
		} catch (Exception e) {
			result = e.getMessage();
		}
		
		response.setResult(result);
		
		return response;
	}

	@PostMapping("/findFlight2")
	public FlightResponse findFlight2(@RequestBody FlightFilterDto filter) {
		
		FlightResponse response = new FlightResponse();
		String result = "Find flight successfully";

		try {

			FlightModel flightModel = flightService.findFlight(filter.getFlightId());
			
			response.setFlightDto(mapperService.map(flightModel, FlightDto.class));
		
		} catch (Exception e) {
			result = e.getMessage();
		}
		
		response.setResult(result);
		
		return response;
	}
	
}
