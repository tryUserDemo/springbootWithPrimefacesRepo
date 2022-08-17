package com.example.demo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.repository.FlightRepository;

import lombok.Getter;
import lombok.Setter;


@Component
@ManagedBean
@ViewScoped
@Getter @Setter
public class FlightViewController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
    private FlightRepository flightRepository;


	private Flight flight;
	
    private List<Flight> flightList = new ArrayList<>();

    
    @PostConstruct
    public void init() {
    	refresh();
    	getAllFlightList();
    }
    
    public void hataMesajiFirlat(String hataMesaji) {
    	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", hataMesaji);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    public void getAllFlightList() {
    	flightList = flightRepository.findAllByOrderByIdDesc();
    }

    public void save() {
    	
    	try {
    		flightRepository.save(flight);
		} catch (Exception e) {
			hataMesajiFirlat("Kaydetme işleminde hata oluştu. Detay = " + e.getMessage());
			return;
		}
        
        refresh();
    	getAllFlightList();
    }

    public void edit(Flight flightItem) {
        this.flight = flightItem;
    }

    public void refresh() {
        flight = new Flight();
    }

}