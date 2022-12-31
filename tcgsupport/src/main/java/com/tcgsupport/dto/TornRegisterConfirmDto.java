package com.tcgsupport.dto;


import lombok.Data;

@Data
public class TornRegisterConfirmDto {

	private String seriesName;
	
	/** name. */
	private String name;

	/** capacity. */
	private String capacity;

	/** local_id. */
	private String localName;

	/** 開催日 */
	private String eventDate;

	/** regulation. */
	private String regulation;

	/** registerTyp. */
	private String registerTyp;

	/** deck_limit. */
	private String deckLimit;

	/** method. */
	private String method;

	/** description. */
	private String description;

	/** icon. */
	private String icon;

	/** public_time. */
	private String  publicstart;

	/** entry_start_time. */
	private String  entryStartTime;

	/** entry_end_time. */
	private String  entryEndTime;
}
