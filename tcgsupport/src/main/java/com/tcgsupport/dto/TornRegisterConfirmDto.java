package com.tcgsupport.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TornRegisterConfirmDto {

	private Integer seriesId;
	private String seriesName;
	
	/** name. */
	private String name;

	/** capacity. */
	private String capacity;

	/** local_id. */
	private String localName;

	/** 開催日 */
	private LocalDate eventDate;
	private String eventDateDsp;

	/** regulation. */
	private String regulation;

	/** registerTyp. */
	private String registerTyp;

	/** deck_limit. */
	private LocalDateTime deckLimit;
	private String deckLimitDsp;

	/** method. */
	private String method;

	/** description. */
	private String description;

	/** icon. */
	private String icon;

	/** public_time. */
	private LocalDateTime  publicstart;
	private String  publicstartDsp;

	/** entry_start_time. */
	private LocalDateTime entryStartTime;
	private String  entryStartTimeDsp;

	/** entry_end_time. */
	private LocalDateTime entryEndTime;
	private String  entryEndTimeDsp;
	
}
