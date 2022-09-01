package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	List<UserInfo> findAllByOrderByUserIdDesc();
	
	UserInfo findByLoginUserNameAndLoginUserPassword(@Param("loginUserName") String userName, @Param("loginUserPassword") String password);
	
}