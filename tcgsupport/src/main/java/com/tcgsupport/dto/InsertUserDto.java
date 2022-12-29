package com.tcgsupport.dto;

import lombok.Data;

/**
 * 初期登録。最初は「登録名」「メアド」「パスワード」のみの想定
 * 登録後→メール→本登録
 * @author nishino
 *
 */
@Data
public class InsertUserDto {
	private String name;
	private String mail;
	private String password;
}
