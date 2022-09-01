package com.example.demo.core.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserInfoModel implements Serializable {

	private static final long serialVersionUID = 1L;


	private Long userId;

    private String firstName;

    private String lastName;

    private String loginUserName;

    private String loginUserPassword;
 
    private String mobilePhone;

    private Date dateOfBirth;

    private String gender;

}
