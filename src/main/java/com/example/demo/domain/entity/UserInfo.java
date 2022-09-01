package com.example.demo.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserInfo {

    @Id
    @GeneratedValue
    private Long userId;

    @Column
    private String firstName;
    
    @Column
    private String lastName;
    
    @Column
    private String loginUserName;

    @Column
    private String loginUserPassword;
    
    @Column
    private String mobilePhone;
    
    @Column
    private Date dateOfBirth;
    
    @Column
    private String gender;
}