package com.tcgsupport.form;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RegisterTornamentInfoForm {

	private Integer seriesId;
	
	/** name. */
	@NotEmpty(message = "{errmsg0201}")
	private String name;

	/** capacity. */
	@NotNull(message = "{errmsg0202}")
	@Pattern(regexp = "^[0-9]+$", message = "{errmsg0203}")
	private String capacity;

	/** local_id. */
	@Min(value=0,message = "{errmsg0204}")
	private Integer localId;

	/** 開催日 */
	@NotNull(message = "{errmsg0208}")
	@Future(message = "{errmsg0209}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate eventDate;

	/** regulation. */
	@Min(value=0,message = "{errmsg0205}")
	private Integer regulation;

	/** registerTyp. */
	@Min(value=0,message = "{errmsg0206}")
	private Integer registerTyp;

	/** deck_limit. */
	@Future(message = "{errmsg0201}")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime deckLimit;

	/** method. */
	@Min(value=0,message = "{errmsg0207}")
	private Integer method;

	/** description. */
	private String description;

	/** icon. */
	private MultipartFile icon;

	/** public_time. */
	@Future(message = "{errmsg0209}")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime  publicstart;

	/** entry_start_time. */
	@Future(message = "{errmsg0209}")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime  entryStartTime;

	/** entry_end_time. */
	@Future(message = "{errmsg0209}")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime  entryEndTime;
}
