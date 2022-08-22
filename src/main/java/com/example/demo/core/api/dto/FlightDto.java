package com.example.demo.core.api.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FlightDto implements Serializable {

	private static final long serialVersionUID = 1L;


	private Long id;

    private String airline;

    private String fromAirport;

    private String toAirport;

    private Date scheduledDateTime;

    private Date estimatedDateTime;

    private Date actualDateTime;

}