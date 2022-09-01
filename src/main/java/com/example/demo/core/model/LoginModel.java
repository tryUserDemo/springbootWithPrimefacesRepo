package com.example.demo.core.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginModel implements Serializable {

	private static final long serialVersionUID = 1L;


    private String userName;

    private String password;

}
