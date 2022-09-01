package com.example.demo.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.core.api.dto.FlightDto;
import com.example.demo.core.model.FlightModel;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.repository.FlightRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FlightService extends BaseService{

	
	private final FlightRepository flightRepository;


	public List<FlightModel> getAllFlightList() {
		return mapList(flightRepository.findAllByOrderByIdDesc(), FlightModel.class);
	}

	public FlightModel saveFlight(FlightDto newFlight) {
		FlightModel newFlightModel = map(newFlight, FlightModel.class);
		return saveFlightByModel(newFlightModel);
	}

	public FlightModel saveFlightByModel(FlightModel newFlightModel) {
		Flight flight = flightRepository.save(map(newFlightModel, Flight.class));
		newFlightModel.setId(flight != null ? flight.getId() : null);
		return newFlightModel;
	}
	
	public FlightModel findFlight(Long flightId) {
		FlightModel model = null;
		try {
			model = map(getFlightById(flightId), FlightModel.class);
		} catch (Exception e) {
			log.error("findFlight error : ", e);
		}
		return model;
	}
	
	public void deleteFlight(Long flightId) {
		flightRepository.deleteById(flightId);
	}
	
	private Flight getFlightById(Long flightId) {
		return flightRepository.findById(flightId).orElseThrow(() -> new DataNotFoundException(flightId));
	}

}
