package com.tcgsupport.param;

public enum MethodEnum {
	DEFAULT(-1,"未選択"),
	SDONLY(0,"スイスドローのみ"),
	KOONLY(1,"トーナメントのみ"),
	LGONLY(2,"リーグ戦のみ"),
	SDKO(3,"スイスドロー＆トーナメント"),
	SDSD(4,"スイスドロー＆スイスドロー"),
	LGKO(5,"リーグ戦＆トーナメント");

	//ステータス
	private int id;
	private String name;

	private MethodEnum(int id, String name) {
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
	
	public static MethodEnum getBy(Integer id) {
		MethodEnum ret = null;
		for(MethodEnum element : MethodEnum.values()) {
			if( element.equals(id) ) {
				ret = element;
				break;
			}
		}
		return ret;
	}
}
