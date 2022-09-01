package com.example.demo.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.core.model.UserInfoModel;
import com.example.demo.domain.entity.UserInfo;
import com.example.demo.domain.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoService extends BaseService{

	private final UserInfoRepository userInfoRepository;


	public List<UserInfoModel> getAllUserList() {
		return mapperService.mapList(userInfoRepository.findAllByOrderByUserIdDesc(), UserInfoModel.class);
	}

	public UserInfoModel saveUser(UserInfoModel newUserInfoModel) {
		UserInfo userInfo = userInfoRepository.save(mapperService.map(newUserInfoModel, UserInfo.class));
		newUserInfoModel.setUserId(userInfo != null ? userInfo.getUserId() : null);
		return newUserInfoModel;
	}
	
	public UserInfoModel findUser(Long userId) {
		UserInfoModel model = null;
		try {
			model = mapperService.map(getUserById(userId), UserInfoModel.class);
		} catch (Exception e) {
			log.error("findFlight error : ", e);
		}
		return model;
	}
	
	public UserInfoModel findUser(String userName, String password) {
		UserInfoModel model = null;
		try {
			model = mapperService.map(getUser(userName, password), UserInfoModel.class);
		} catch (Exception e) {
			log.error("findFlight error : ", e);
		}
		return model;
	}
	
	public void deleteUser(Long userId) {
		userInfoRepository.deleteById(userId);
	}
	
	private UserInfo getUserById(Long userId) {
		return userInfoRepository.findById(userId).orElseThrow(() -> new DataNotFoundException(userId));
	}
	
	private UserInfo getUser(String userName, String password) {
		UserInfo findUser = null;
		try {
			findUser = userInfoRepository.findByLoginUserNameAndLoginUserPassword(userName, password);	
		} catch (Exception e) {
			log.error("getUser error : ", e);
		}
		return findUser;
	}
	
}
