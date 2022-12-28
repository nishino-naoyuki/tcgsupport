package com.tcgsupport.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "ptcgs.cconfig")
@Data
public class SystemConfig {
	static SystemConfig config;
	
	@Autowired
	public void setCofing(SystemConfig config) {
		SystemConfig.config = config;
	}
	
	public static SystemConfig getInstance() {
		return SystemConfig.config;
	}

	private String salt;	//パスワードハッシュソルト
	private String avaterbasedir;//
}
