package com.tcgsupport.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;

/**
 * 大会テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="TORN_TBL")
public class TornTblEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** torn_id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tornId;

	/** 新規テーブル. */
	private Integer seriesId;
	@OneToOne
    @JoinColumn(name="seriesId",insertable=false ,updatable=false)
	private SeriesTblEntity seriesTbl;

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

	/** day. */
	private Integer day;
	
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

	/** 参加者テーブル 一覧. */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="tornId",insertable=true ,updatable=true)
	@OrderBy(value = "entryTime desc")
	private Set<EntrantTblEntity> entrantTblSet;

	/** 試合テーブル 一覧. */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="tornId",insertable=true ,updatable=true)
	private Set<GameTblEntity> gameTblSet;

	/** ルールテーブル 一覧. */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="tornId",insertable=true ,updatable=true)
	private Set<RuleTblEntity> ruleTblSet;
	
	private Integer userId;
	@OneToOne
    @JoinColumn(name="userId",insertable=false ,updatable=false)
	private UserTblEntity userTbl;

	/**
	 * コンストラクタ.
	 */
	public TornTblEntity() {
		this.entrantTblSet = new HashSet<EntrantTblEntity>();
		this.gameTblSet = new HashSet<GameTblEntity>();
		this.ruleTblSet = new HashSet<RuleTblEntity>();
	}

}
