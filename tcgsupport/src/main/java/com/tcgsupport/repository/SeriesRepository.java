package com.tcgsupport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tcgsupport.entity.SeriesTblEntity;

public interface SeriesRepository 
	extends JpaSpecificationExecutor<SeriesTblEntity>, JpaRepository<SeriesTblEntity, Integer>{

	SeriesTblEntity findByName(String name);
}
