package com.tcgsupport.param;

public enum LocalEnum {
	DEFAULT(-1,"未選択"),
	HOKKAIDO(0,"北海道"),
	AOMORI(1,"青森県"),
	IWATE(2,"岩手県"),
	MIYAGI(3,"宮城県"),
	AKITA(4,"秋田県"),
	YAMAGATA(5,"山形県"),
	FUKUSHIMA(6,"福島県"),
	IBARAKI(7,"茨城県"),
	TOCHIGI(8,"栃木県"),
	GUNMA(9,"群馬県"),
	SAITAMA(10,"埼玉県"),
	CHIBA(11,"千葉県"),
	TOKYO(12,"東京都"),
	KANAGAWA(13,"神奈川県"),
	NIIGATA(14,"新潟県"),
	TOYAMA(15,"富山県"),
	ISHIKAWA(16,"石川県"),
	FUKUI(17,"福井県"),
	YAMANASHI(18,"山梨県"),
	NAGANO(19,"長野県"),
	GIFU(20,"岐阜県"),
	SHIZUOKA(21,"静岡県"),
	AICHI(22,"愛知県"),
	MIE(23,"三重県"),
	SHIGA(24,"滋賀県"),
	KYOTO(25,"京都府"),
	OSAKA(26,"大阪府"),
	HYOGO(27,"兵庫県"),
	NARA(28,"奈良県"),
	WAKAYAMA(29,"和歌山県"),
	TOTTORI(30,"鳥取県"),
	SHIMANE(31,"島根県"),
	OKAYAMA(32,"岡山県"),
	HIROSHIMA(33,"広島県"),
	YAMAGUCHI(34,"山口県"),
	TOKUSHIMA(35,"徳島県"),
	KAGAWA(36,"香川県"),
	EHIME(37,"愛媛県"),
	KOCHI(38,"高知県"),
	FUKUOKA(39,"福岡県"),
	SAGA(40,"佐賀県"),
	NAGASAKI(41,"長崎県"),
	KUMAMOTO(42,"熊本県"),
	OITA(43,"大分県"),
	MIYAZAKI(44,"宮崎県"),
	KAGOSHIMA(45,"鹿児島県"),
	OKINAWA(46,"沖縄県");

	//ステータス
	private int id;
	private String name;

	private LocalEnum(int id, String name) {
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
	
	public static LocalEnum getBy(Integer id) {
		LocalEnum ret = null;
		for(LocalEnum element : LocalEnum.values()) {
			if( element.equals(id) ) {
				ret = element;
				break;
			}
		}
		return ret;
	}
}
