package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	List<Flight> findAllByOrderByIdDesc();
	
}