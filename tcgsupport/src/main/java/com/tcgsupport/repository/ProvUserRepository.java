package com.tcgsupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tcgsupport.entity.ProvUserEntity;

public interface ProvUserRepository 
	extends JpaSpecificationExecutor<ProvUserEntity>, JpaRepository<ProvUserEntity, Integer>{

}
