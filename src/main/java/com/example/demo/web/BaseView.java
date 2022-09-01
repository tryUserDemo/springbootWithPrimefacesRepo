package com.example.demo.web;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.tomcat.util.codec.binary.Base64;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Component
@Getter @Setter
public class BaseView{

	    
    public void hataMesajiFirlat(String hataMesaji) {
    	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", hataMesaji);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    public void uyariMesajiFirlat(String hataMesaji) {
    	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "", hataMesaji);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    public void bilgiMesajiFirlat(String hataMesaji) {
    	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "", hataMesaji);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    public void showDialog(String componentId) {
    	PrimeFaces.current().executeScript("PF('" + componentId + "').show();");
    }
    
    public void closeDialog(String componentId) {
    	PrimeFaces.current().executeScript("PF('" + componentId + "').hide();");
    }
    
    public String decode(String encodedString) {
    	return new String(Base64.decodeBase64(encodedString));
    }
    
    public String encode(String plainText) {
    	return Base64.encodeBase64String(plainText.getBytes());
    }

}