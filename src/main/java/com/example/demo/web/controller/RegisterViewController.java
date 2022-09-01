package com.example.demo.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.stereotype.Component;

import com.example.demo.core.model.UserInfoModel;
import com.example.demo.core.service.UserInfoService;
import com.example.demo.web.BaseView;

import lombok.Getter;
import lombok.Setter;

@Component
@ManagedBean
@ViewScoped
@Getter @Setter
public class RegisterViewController extends BaseView {

	private final UserInfoService userInfoService;

	RegisterViewController(UserInfoService service) {
		this.userInfoService = service;
	}

	private UserInfoModel userModel;

	private UserInfoModel selectedUserModel;

	private List<UserInfoModel> userList = new ArrayList<>();


	@PostConstruct
	public void init() {
		refresh();
		getAllUserInfoList();
	}

	public void getAllUserInfoList() {
		userList = userInfoService.getAllUserList();
	}

	public void save() {
		try {
			userModel.setLoginUserPassword(encode(userModel.getLoginUserPassword()));
			userInfoService.saveUser(userModel);
			bilgiMesajiFirlat("Kaydetme işlemi başarılı.");
			init();
		} catch (Exception e) {
			hataMesajiFirlat("Kaydetme işleminde hata oluştu. Detay = " + e.getMessage());
		}
	}

	public void edit(UserInfoModel userItem) {
		this.userModel = userItem;
	}

	public void onClickDelete(UserInfoModel userItem) {
		selectedUserModel = userItem;
		showDeleteDialog();
	}

	public void delete() {
		try {
			userInfoService.deleteUser(selectedUserModel.getUserId());
			bilgiMesajiFirlat("Silme işlemi başarılı.");
			getAllUserInfoList();
		} catch (Exception e) {
			hataMesajiFirlat("Silme işleminde hata oluştu. Detay = " + e.getMessage());
		} finally {
			closeDeleteDialog();
		}
	}

	public void showDeleteDialog() {
		showDialog("deleteConfirmDialog");
	}

	public void closeDeleteDialog() {
		selectedUserModel = null;
		closeDialog("deleteConfirmDialog");
	}

	public void refresh() {
		userModel = new UserInfoModel();
		selectedUserModel = null;
	}

}