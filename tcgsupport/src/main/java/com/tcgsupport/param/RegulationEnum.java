package com.tcgsupport.param;

public enum RegulationEnum {
	DEFAULT(-1,"未選択"),
	HOKKAIDO(0,"スタンダード"),
	AOMORI(1,"エクストラ県"),
	IWATE(2,"特殊");

	//ステータス
	private int id;
	private String name;

	private RegulationEnum(int id, String name) {
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
	
	public static RegulationEnum getBy(Integer id) {
		RegulationEnum ret = null;
		for(RegulationEnum element : RegulationEnum.values()) {
			if( element.equals(id) ) {
				ret = element;
				break;
			}
		}
		return ret;
	}
}
