package com.example.demo.repository;

import com.example.demo.model.Flight;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	List<Flight> findAllByOrderByIdDesc();
	
}