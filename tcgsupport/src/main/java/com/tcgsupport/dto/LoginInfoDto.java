package com.tcgsupport.dto;

import lombok.Data;

@Data
public class LoginInfoDto {

	private Integer userId;
	private String name;
	private String mail;
	private Integer adminflg;
	private String icon;
	private Integer certification;
}
