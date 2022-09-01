package com.example.demo.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.stereotype.Component;

import com.example.demo.common.AppUtils;
import com.example.demo.core.model.FlightModel;
import com.example.demo.core.service.FlightService;
import com.example.demo.web.BaseView;

import lombok.Getter;
import lombok.Setter;


@Component
@ManagedBean
@ViewScoped
@Getter @Setter
public class FlightViewController extends BaseView{

	private final FlightService flightService;
	
	FlightViewController(FlightService service) {
		this.flightService = service;
	}

	private FlightModel flightModel;
	
	private FlightModel selectedFlightModel;
	
    private List<FlightModel> flightList = new ArrayList<>();

    private String searchFlightItemId;
    
    
    @PostConstruct
    public void init() {
    	refresh();
        refreshSearch();
    	getAllFlightList();
    }
    
    public void getAllFlightList() {
    	flightList = flightService.getAllFlightList();
    }

    public void save() {
    	try {
    		flightService.saveFlightByModel(flightModel);
    		bilgiMesajiFirlat("Kaydetme işlemi başarılı.");
            init();
		} catch (Exception e) {
			hataMesajiFirlat("Kaydetme işleminde hata oluştu. Detay = " + e.getMessage());
		}
    }

    public void search() {
    	try {
    		if(AppUtils.nullOrBlank(searchFlightItemId)) {
    			uyariMesajiFirlat("Lütfen arama yapacağınız uçuş id bilgisini giriniz.");
    			return;
    		} else if(!AppUtils.isNumeric(searchFlightItemId)) {
    			uyariMesajiFirlat("Arama yapılan uçuş id bilgisi numerik olmalıdır.");
    			return;
    		}
    		FlightModel resultModel = flightService.findFlight(Long.parseLong(searchFlightItemId)); 
    		if(resultModel == null) {
    			uyariMesajiFirlat("Girilen bilgilere ait uçuş bulunamadı.");
    			getAllFlightList();
    		} else {
    			bilgiMesajiFirlat("Girilen bilgilere ait veri bulundu.");
	    		flightList.clear();
	            flightList.add(resultModel);
            }
		} catch (Exception e) {
			hataMesajiFirlat("Arama işleminde hata oluştu. Detay = " + e.getMessage());
		} finally {
			searchFlightItemId = null;
		}
    }
    
    public void edit(FlightModel flightItem) {
        this.flightModel = flightItem;
    }

    public void onClickDelete(FlightModel flightItem) {
    	selectedFlightModel = flightItem;
    	showDeleteDialog();
    }
    
    public void delete() {
    	try {
    		flightService.deleteFlight(selectedFlightModel.getId());
    		bilgiMesajiFirlat("Silme işlemi başarılı.");
        	getAllFlightList();
		} catch (Exception e) {
			hataMesajiFirlat("Silme işleminde hata oluştu. Detay = " + e.getMessage());
		}finally {
			closeDeleteDialog();
		}
    }
    
    public void showDeleteDialog() {
    	showDialog("deleteConfirmDialog");
    }
    
    public void closeDeleteDialog() {
    	selectedFlightModel = null;
    	closeDialog("deleteConfirmDialog");
    }
    
    public void refresh() {
        flightModel = new FlightModel();
        selectedFlightModel = null;
        searchFlightItemId = null;
    }
    
    public void refreshSearch() {
        searchFlightItemId = null;
    }

}