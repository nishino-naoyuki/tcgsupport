package com.tcgsupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tcgsupport.entity.TornTblEntity;

public interface TornRepository 
	extends JpaSpecificationExecutor<TornTblEntity>, JpaRepository<TornTblEntity, Integer>{

}
