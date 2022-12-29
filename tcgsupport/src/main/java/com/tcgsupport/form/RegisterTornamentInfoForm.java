package com.tcgsupport.form;

import java.util.Date;

import lombok.Data;

@Data
public class RegisterTornamentInfoForm {

	private Integer seriesId;
	
	/** name. */
	private String name;

	/** capacity. */
	private Integer capacity;

	/** local_id. */
	private Integer localId;

	/** year. */
	private Integer year;

	/** month. */
	private Integer month;

	/** regulation. */
	private Integer regulation;

	/** reg_typ. */
	private Integer regTyp;

	/** deck_limit. */
	private Date deckLimit;

	/** method. */
	private Integer method;

	/** description. */
	private String description;

	/** icon. */
	private String icon;

	/** public_time. */
	private Date publicTime;

	/** entry_start_time. */
	private Date entryStartTime;

	/** entry_end_time. */
	private Date entryEndTime;
}
