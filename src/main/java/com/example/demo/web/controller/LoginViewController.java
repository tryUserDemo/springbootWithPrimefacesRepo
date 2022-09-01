package com.example.demo.web.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.stereotype.Component;

import com.example.demo.core.model.LoginModel;
import com.example.demo.core.model.UserInfoModel;
import com.example.demo.core.service.UserInfoService;
import com.example.demo.web.BaseView;

import lombok.Getter;
import lombok.Setter;

@Component
@ManagedBean
@ViewScoped
@Getter @Setter
public class LoginViewController extends BaseView {

	private final UserInfoService userInfoService;

	LoginViewController(UserInfoService service) {
		this.userInfoService = service;
	}

	private LoginModel loginModel;



	@PostConstruct
	public void init() {
		refresh();
	}

	public void loginControl() {
		try {
			UserInfoModel loginUser = userInfoService.findUser(loginModel.getUserName(), encode(loginModel.getPassword()));
			if(loginUser != null) {
				bilgiMesajiFirlat("Giriş başarılı.");
			} else {
				hataMesajiFirlat("Giriş başarısız.");
			}
		    init();
		} catch (Exception e) {
			hataMesajiFirlat("Login işleminde hata oluştu. Detay = " + e.getMessage());
		}
	}
	
	public void showInfoDialog() {
		showDialog("infoDialog");
	}
	
	public void closeInfoDialog() {
		closeDialog("infoDialog");
	}

	public void refresh() {
		loginModel = new LoginModel();
	}

}