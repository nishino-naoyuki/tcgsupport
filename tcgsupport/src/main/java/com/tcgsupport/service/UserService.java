package com.tcgsupport.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tcgsupport.repository.UserRepository;

public class UserService {
	@Autowired 
	UserRepository userRepository;
	
	public LoginInfoDto login(String mail,String pass) {
		LoginInfoDto dto = null;
		return dto;
	}
}
