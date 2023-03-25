package com.tcgsupport.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tcgsupport.repository.ProvUserRepository;
import com.tcgsupport.repository.SeriesRepository;
import com.tcgsupport.repository.TornRepository;
import com.tcgsupport.repository.UserRepository;

public class ServiceBase {

	@Autowired 
	protected UserRepository userRepository;
	@Autowired 
	protected ProvUserRepository provUserRepository;
	@Autowired
	protected TornRepository tornRepository;
	@Autowired
	protected SeriesRepository seriesRepository;
}
