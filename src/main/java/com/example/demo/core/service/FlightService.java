package com.example.demo.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.FlightNotFoundException;
import com.example.demo.core.model.FlightModel;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.repository.FlightRepository;
import com.example.demo.integratiom.MapperService;

@Service
public class FlightService {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	MapperService mapperService;


	List<Flight> getAllFlightList() {
		return flightRepository.findAllByOrderByIdDesc();
	}

	Flight getFlightById(Long flightId) {
		return flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException(flightId));
	}

	Flight saveFlight(Flight newFlight) {
		return flightRepository.save(newFlight);
	}

	void deleteFlight(Long flightId) {
		flightRepository.deleteById(flightId);
	}

	public FlightModel findFlight(Long flightId) {

		FlightModel model = null;

		try {

			model = mapperService.map(getFlightById(flightId), FlightModel.class);

		} catch (Exception e) {
			//TODO : Logger
		}

		return model;
	}
	
	
}
