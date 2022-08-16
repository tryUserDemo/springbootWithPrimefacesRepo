package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String airline;
    
    @Column
    private String fromAirport;
    
    @Column
    private String toAirport;

    @Column
    private Date scheduledDateTime;
    
    @Column
    private Date estimatedDateTime;
    
    @Column
    private Date actualDateTime;

}