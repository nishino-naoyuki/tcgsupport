package com.tcgsupport.form;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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

	/** 開催日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate eventDate;

	/** regulation. */
	private Integer regulation;

	/** registerTyp. */
	private Integer registerTyp;

	/** deck_limit. */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime deckLimit;

	/** method. */
	private Integer method;

	/** description. */
	private String description;

	/** icon. */
	private MultipartFile icon;

	/** public_time. */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime  publicstart;

	/** entry_start_time. */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime  entryStartTime;

	/** entry_end_time. */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime  entryEndTime;
}
