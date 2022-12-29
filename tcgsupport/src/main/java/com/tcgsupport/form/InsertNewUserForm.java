package com.tcgsupport.form;

import lombok.Data;

@Data
public class InsertNewUserForm {
	private String name;
	private String mail;
	private String password;
}
