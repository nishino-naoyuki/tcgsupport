package com.tcgsupport.service;

import org.springframework.stereotype.Service;

import com.tcgsupport.entity.SeriesTblEntity;
import com.tcgsupport.form.SeriesForm;

@Service
public class SeriesService extends ServiceBase{

	/**
	 * シリーズ
	 * @param seriesForm
	 */
	public void insert(SeriesForm seriesForm) {
		SeriesTblEntity seriesTblEntity = new SeriesTblEntity();
		
		seriesTblEntity.setDiscription( seriesForm.getSeriesDescription() );
		seriesTblEntity.setName( seriesForm.getTitle().replace("　"," ") );
		
		seriesRepository.save(seriesTblEntity);
	}
	
	/**
	 * 指定されたタイトルが存在するかどうかを取得する
	 * @param name
	 * @return
	 */
	public boolean isExist(String name) {
		SeriesTblEntity seriesTblEntity = seriesRepository.findByName( name.replace("　"," ") );
		
		return (seriesTblEntity!=null);
	}
}
