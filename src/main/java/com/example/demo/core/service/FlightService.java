package com.example.demo.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.common.FlightNotFoundException;
import com.example.demo.core.api.dto.FlightDto;
import com.example.demo.core.model.FlightModel;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.repository.FlightRepository;
import com.example.demo.integratiom.MapperService;

@Service
public class FlightService {

	private final FlightRepository flightRepository;
	private final MapperService mapperService;
	
	FlightService(FlightRepository repo, MapperService mapper) {
		this.flightRepository = repo;
		this.mapperService = mapper;
	}


	public List<FlightModel> getAllFlightList() {
		return mapperService.mapList(flightRepository.findAllByOrderByIdDesc(), FlightModel.class);
	}

	public FlightModel saveFlight(FlightDto newFlight) {
		FlightModel newFlightModel = mapperService.map(newFlight, FlightModel.class);
		return saveFlightByModel(newFlightModel);
	}

	public FlightModel saveFlightByModel(FlightModel newFlightModel) {
		Flight flight = flightRepository.save(mapperService.map(newFlightModel, Flight.class));
		newFlightModel.setId(flight != null ? flight.getId() : null);
		return newFlightModel;
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
	
	public void deleteFlight(Long flightId) {
		flightRepository.deleteById(flightId);
	}
	
	private Flight getFlightById(Long flightId) {
		return flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException(flightId));
	}

}
