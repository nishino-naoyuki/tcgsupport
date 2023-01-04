package com.tcgsupport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcgsupport.dto.TornRegisterConfirmDto;
import com.tcgsupport.entity.TornTblEntity;
import com.tcgsupport.repository.TornRepository;
import com.tcgsupport.util.Exchange;

@Service
public class TornService {
	@Autowired
	TornRepository tornRepository;

	public void insert(TornRegisterConfirmDto dto,Integer orgId) {
		//enttyを取得
		TornTblEntity tornEntity = getTornTblEntity(dto,orgId);
		
		tornRepository.save(tornEntity);
	}
	
	/* =private= */
	private TornTblEntity getTornTblEntity(TornRegisterConfirmDto dto,Integer orgId) {
		TornTblEntity tornEntity = new TornTblEntity();
		
		tornEntity.setSeriesId(dto.getSeriesId());
		tornEntity.setName(dto.getName());
		tornEntity.setCapacity( Integer.parseInt(dto.getCapacity()) );
		tornEntity.setLocalId(dto.getLocalId());
		tornEntity.setYear(dto.getEventDate().getYear());
		tornEntity.setMonth(dto.getEventDate().getMonthValue());
		tornEntity.setDay(dto.getEventDate().getDayOfMonth());
		tornEntity.setRegulation(dto.getRegulationId());
		tornEntity.setRegTyp(dto.getRegisterTypId());
		if(dto.getDeckLimit() != null) {
			tornEntity.setDeckLimit(
					Exchange.toDate( dto.getDeckLimit() ));
		}
		tornEntity.setMethod(dto.getMethodId());
		tornEntity.setDescription(dto.getDescription());
		tornEntity.setIcon(dto.getIcon());
		tornEntity.setPublicTime( Exchange.toTimestamp(dto.getPublicstart()) );
		if(dto.getEntryStartTime() == null) {
			//未指定の場合は公開と同時
			tornEntity.setEntryStartTime(Exchange.toTimestamp(dto.getPublicstart()));
		}else {
			tornEntity.setEntryStartTime(Exchange.toTimestamp(dto.getEntryStartTime()));
		}

		if(dto.getEntryEndTime() == null) {
			//未指定の場合は開催日
			tornEntity.setEntryStartTime(Exchange.toDate(dto.getEventDate()));
		}else {
			tornEntity.setEntryStartTime(Exchange.toTimestamp(dto.getEntryEndTime()));
		}
		
		return tornEntity;
	}
}
