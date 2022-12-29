package com.tcgsupport.dto;

import lombok.Data;

@Data
public class UserInfoDto {

	private Integer userId;
	private String name;
	private String mail;
	private Boolean adminflg;
	private String icon;
	private Boolean certification;
}
