package com.tcgsupport.param;

public enum RegiTypeEnum {
	DEFAULT(-1,"未選択"),
	FIRSTCOME(0,"先着順"),
	LOTTERY(1,"抽選");

	//ステータス
	private int id;
	private String name;

	private RegiTypeEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean equals(Integer id) {
		boolean ret = false;
		if( id != null) {
			ret = (this.id == id);
		}
		return ret;
	}
	
	public static RegiTypeEnum getBy(Integer id) {
		RegiTypeEnum ret = null;
		for(RegiTypeEnum element : RegiTypeEnum.values()) {
			if( element.equals(id) ) {
				ret = element;
				break;
			}
		}
		return ret;
	}
}
