package com.tcgsupport.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * 参加者テーブル モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
@Data
@Entity 
@Table(name="ENTRANT_TBL")
public class EntrantTblEntity implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** entrant_id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer entrantId;
	
	/** 大会テーブル. */
	private Integer tornId;
	@OneToOne
    @JoinColumn(name="tornId",insertable=false ,updatable=false)
	private TornTblEntity tornTbl;

	/** 新規テーブル. */
	private Integer userId;
	@OneToOne
    @JoinColumn(name="userId",insertable=false ,updatable=false)
	private UserTblEntity userTbl;

	/** invite. */
	private Integer invite;

	/** entry_time. */
	private Date entryTime;

	/** state. */
	private Integer state;

	/** win_num. */
	private Integer winNum;

	/** lose_num. */
	private Integer loseNum;

	/** point. */
	private Integer point;

	/** deckcode. */
	private Integer deckcode;

	/**
	 * コンストラクタ.
	 */
	public EntrantTblEntity() {
	}

	/**
	 * 大会テーブル を設定します.
	 * 
	 * @param tornTbl
	 *            大会テーブル
	 */
	public void setTornTbl(TornTblEntity tornTbl) {
		this.tornTbl = tornTbl;
	}

	/**
	 * 大会テーブル を取得します.
	 * 
	 * @return 大会テーブル
	 */
	public TornTblEntity getTornTbl() {
		return this.tornTbl;
	}

	/**
	 * 新規テーブル を設定します.
	 * 
	 * @param userTbl
	 *            新規テーブル
	 */
	public void setUserTbl(UserTblEntity userTbl) {
		this.userTbl = userTbl;
	}

	/**
	 * 新規テーブル を取得します.
	 * 
	 * @return 新規テーブル
	 */
	public UserTblEntity getUserTbl() {
		return this.userTbl;
	}

	/**
	 * invite を設定します.
	 * 
	 * @param invite
	 *            invite
	 */
	public void setInvite(Integer invite) {
		this.invite = invite;
	}

	/**
	 * invite を取得します.
	 * 
	 * @return invite
	 */
	public Integer getInvite() {
		return this.invite;
	}

	/**
	 * entry_time を設定します.
	 * 
	 * @param entryTime
	 *            entry_time
	 */
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	/**
	 * entry_time を取得します.
	 * 
	 * @return entry_time
	 */
	public Date getEntryTime() {
		return this.entryTime;
	}

	/**
	 * state を設定します.
	 * 
	 * @param state
	 *            state
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * state を取得します.
	 * 
	 * @return state
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 * win_num を設定します.
	 * 
	 * @param winNum
	 *            win_num
	 */
	public void setWinNum(Integer winNum) {
		this.winNum = winNum;
	}

	/**
	 * win_num を取得します.
	 * 
	 * @return win_num
	 */
	public Integer getWinNum() {
		return this.winNum;
	}

	/**
	 * lose_num を設定します.
	 * 
	 * @param loseNum
	 *            lose_num
	 */
	public void setLoseNum(Integer loseNum) {
		this.loseNum = loseNum;
	}

	/**
	 * lose_num を取得します.
	 * 
	 * @return lose_num
	 */
	public Integer getLoseNum() {
		return this.loseNum;
	}

	/**
	 * point を設定します.
	 * 
	 * @param point
	 *            point
	 */
	public void setPoint(Integer point) {
		this.point = point;
	}

	/**
	 * point を取得します.
	 * 
	 * @return point
	 */
	public Integer getPoint() {
		return this.point;
	}

	/**
	 * deckcode を設定します.
	 * 
	 * @param deckcode
	 *            deckcode
	 */
	public void setDeckcode(Integer deckcode) {
		this.deckcode = deckcode;
	}

	/**
	 * deckcode を取得します.
	 * 
	 * @return deckcode
	 */
	public Integer getDeckcode() {
		return this.deckcode;
	}


}
