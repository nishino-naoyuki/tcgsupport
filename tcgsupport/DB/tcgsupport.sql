SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS ENTRANT_TBL;
DROP TABLE IF EXISTS GAME_TBL;
DROP TABLE IF EXISTS PROV_USER;
DROP TABLE IF EXISTS RULE_TBL;
DROP TABLE IF EXISTS SERIESE_POINT;
DROP TABLE IF EXISTS TORN_TBL;
DROP TABLE IF EXISTS SERIES_TBL;
DROP TABLE IF EXISTS USER_TBL;




/* Create Tables */

CREATE TABLE ENTRANT_TBL
(
	entrant_id int NOT NULL AUTO_INCREMENT,
	-- 大会ID
	torn_id int NOT NULL COMMENT '大会ID',
	-- 参加者ID
	user_id int NOT NULL COMMENT '参加者ID',
	-- 招待選手フラグ
	invite int NOT NULL COMMENT '招待選手フラグ',
	-- 登録日
	entry_time timestamp NOT NULL COMMENT '登録日',
	-- 0:参加確定
	-- 1:キャンセル済み
	-- 2:キャンセル待ち
	-- 3:抽選待ち
	-- 4:抽選落
	-- 5:当日未チェックイン
	-- 6:チェックイン済み
	-- 7:ドロップ済み
	state int NOT NULL COMMENT '0:参加確定
1:キャンセル済み
2:キャンセル待ち
3:抽選待ち
4:抽選落
5:当日未チェックイン
6:チェックイン済み
7:ドロップ済み',
	-- この大会での勝数
	win_num int NOT NULL COMMENT 'この大会での勝数',
	-- この大会での負け数
	lose_num int NOT NULL COMMENT 'この大会での負け数',
	-- この大会でのポイント
	point int NOT NULL COMMENT 'この大会でのポイント',
	-- デッキコード
	-- nullは未登録
	deckcode int COMMENT 'デッキコード
nullは未登録',
	PRIMARY KEY (entrant_id)
);


CREATE TABLE GAME_TBL
(
	-- 試合ID
	game_id int NOT NULL AUTO_INCREMENT COMMENT '試合ID',
	-- 大会ID
	torn_id int NOT NULL COMMENT '大会ID',
	-- 参加者ID
	user_id1 int NOT NULL COMMENT '参加者ID',
	-- 参加者ID
	user_id2 int NOT NULL COMMENT '参加者ID',
	-- 卓番号
	table_no int NOT NULL COMMENT '卓番号',
	-- 0:試合前
	-- 1:user1の勝
	-- 2:user2の勝
	-- 3:両負け
	-- 4:引き分け
	state int NOT NULL COMMENT '0:試合前
1:user1の勝
2:user2の勝
3:両負け
4:引き分け',
	-- 何戦目
	round int NOT NULL COMMENT '何戦目',
	-- 試合タイプ
	-- 0:予選スイスドロー
	-- 1:予選トーナメント
	-- 2:予選リーグ
	-- 3:決勝スイスドロー
	-- 4:決勝トーナメント
	-- 5:決勝リーグ
	type int NOT NULL COMMENT '試合タイプ
0:予選スイスドロー
1:予選トーナメント
2:予選リーグ
3:決勝スイスドロー
4:決勝トーナメント
5:決勝リーグ',
	result int NOT NULL,
	PRIMARY KEY (game_id)
);


CREATE TABLE PROV_USER
(
	id int NOT NULL,
	-- メールアドレス
	mail varchar(256) NOT NULL COMMENT 'メールアドレス',
	-- URLにつけるトークン
	token varchar(128) NOT NULL COMMENT 'URLにつけるトークン',
	-- トークンの有効期限
	expiration_date timestamp NOT NULL COMMENT 'トークンの有効期限'
);


CREATE TABLE RULE_TBL
(
	rule_id int NOT NULL AUTO_INCREMENT,
	-- 大会ID
	torn_id int NOT NULL COMMENT '大会ID',
	-- 時間切れの時に対応
	-- 0:両負け
	-- 1:引き分け
	timeout int NOT NULL COMMENT '時間切れの時に対応
0:両負け
1:引き分け',
	-- 勝ち点
	win_point int NOT NULL COMMENT '勝ち点',
	-- 引き分けの時のポイント
	draw_point int NOT NULL COMMENT '引き分けの時のポイント',
	-- オポネントの計算方法
	-- 最低勝率デフォルトでは0.33
	lower_win_rate decimal NOT NULL COMMENT 'オポネントの計算方法
最低勝率デフォルトでは0.33',
	-- 1位のポイント（通算成績に加算）
	rank1_point int NOT NULL COMMENT '1位のポイント（通算成績に加算）',
	-- 2位のポイント
	rank2_point int NOT NULL COMMENT '2位のポイント',
	-- 3位のポイント
	rank3_point int NOT NULL COMMENT '3位のポイント',
	-- 4位ポイント
	rank4_point int NOT NULL COMMENT '4位ポイント',
	PRIMARY KEY (rule_id)
);


CREATE TABLE SERIESE_POINT
(
	id int NOT NULL AUTO_INCREMENT,
	series_id int NOT NULL,
	-- 参加者ID
	user_id int NOT NULL COMMENT '参加者ID',
	point int NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE SERIES_TBL
(
	series_id int NOT NULL AUTO_INCREMENT,
	-- メインの主催者ID
	main_organizer_id int NOT NULL COMMENT 'メインの主催者ID',
	-- サブ主催者１
	sub_organizer_id1 int COMMENT 'サブ主催者１',
	-- サブ主催者２
	sub_organizer_id2 int COMMENT 'サブ主催者２',
	-- サブ主催者３
	sub_organizer_id3 int COMMENT 'サブ主催者３',
	-- シリーズ名
	name varchar(200) NOT NULL COMMENT 'シリーズ名',
	-- 説明
	discription varchar(2000) COMMENT '説明',
	-- シリーズアイコン
	icon varchar(256) COMMENT 'シリーズアイコン',
	PRIMARY KEY (series_id)
);


CREATE TABLE TORN_TBL
(
	-- 大会ID
	torn_id int NOT NULL AUTO_INCREMENT COMMENT '大会ID',
	series_id int NOT NULL,
	-- 大会名
	name varchar(100) NOT NULL COMMENT '大会名',
	-- 参加定員
	capacity int NOT NULL COMMENT '参加定員',
	-- 開催場所ID
	local_id int NOT NULL COMMENT '開催場所ID',
	-- 開催年
	year int NOT NULL COMMENT '開催年',
	-- 開催月
	month int NOT NULL COMMENT '開催月',
	-- レギュレーション
	-- 0:スタンダード
	-- 1:エクストラ
	-- 2:殿堂
	-- 3:特殊
	regulation int NOT NULL COMMENT 'レギュレーション
0:スタンダード
1:エクストラ
2:殿堂
3:特殊',
	-- エントリー方法
	-- 0:先着
	-- 1:抽選
	reg_typ int NOT NULL COMMENT 'エントリー方法
0:先着
1:抽選',
	-- デッキ登録期限
	-- nullの場合はデッキ登録は不要とする
	deck_limit timestamp COMMENT 'デッキ登録期限
nullの場合はデッキ登録は不要とする',
	-- 大会方式
	-- 0:スイスドロー
	-- 1:スイスドロー＋決勝トーナメント
	-- 2:決勝トーナメントのみ
	-- 3:リーグ戦
	method int NOT NULL COMMENT '大会方式
0:スイスドロー
1:スイスドロー＋決勝トーナメント
2:決勝トーナメントのみ
3:リーグ戦',
	-- 説明
	description varchar(2000) COMMENT '説明',
	-- 大会アイコン
	icon varchar(256) COMMENT '大会アイコン',
	-- 情報公開日時
	public_time timestamp NOT NULL COMMENT '情報公開日時',
	-- 登録貸し日時
	-- nullの場合は公開と同時に募集開始
	entry_start_time timestamp COMMENT '登録貸し日時
nullの場合は公開と同時に募集開始',
	-- 受付終了時間
	-- nullだと前日の23:59まで可能
	entry_end_time timestamp COMMENT '受付終了時間
nullだと前日の23:59まで可能',
	PRIMARY KEY (torn_id)
);


CREATE TABLE USER_TBL
(
	-- 参加者ID
	user_id int NOT NULL AUTO_INCREMENT COMMENT '参加者ID',
	-- ハンドルネーム
	name varchar(100) NOT NULL COMMENT 'ハンドルネーム',
	-- メールアドレス
	mail varchar(256) NOT NULL COMMENT 'メールアドレス',
	-- パスワードハッシュ
	password varchar(100) NOT NULL COMMENT 'パスワードハッシュ',
	-- 管理者フラグ
	adminflg int NOT NULL COMMENT '管理者フラグ',
	-- 説明
	description varchar(2000) NOT NULL COMMENT '説明',
	-- アイコンのファイル名
	icon varchar(256) COMMENT 'アイコンのファイル名',
	-- 認証済みかどうか
	-- 0:未認証
	-- 1:認証済み
	certification int NOT NULL COMMENT '認証済みかどうか
0:未認証
1:認証済み',
	-- 登録日
	regtime timestamp NOT NULL COMMENT '登録日',
	-- 更新日
	updatetime timestamp NOT NULL COMMENT '更新日',
	PRIMARY KEY (user_id),
	UNIQUE (mail)
);



/* Create Foreign Keys */

ALTER TABLE SERIESE_POINT
	ADD FOREIGN KEY (series_id)
	REFERENCES SERIES_TBL (series_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE TORN_TBL
	ADD FOREIGN KEY (series_id)
	REFERENCES SERIES_TBL (series_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ENTRANT_TBL
	ADD FOREIGN KEY (torn_id)
	REFERENCES TORN_TBL (torn_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE GAME_TBL
	ADD FOREIGN KEY (torn_id)
	REFERENCES TORN_TBL (torn_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE RULE_TBL
	ADD FOREIGN KEY (torn_id)
	REFERENCES TORN_TBL (torn_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ENTRANT_TBL
	ADD FOREIGN KEY (user_id)
	REFERENCES USER_TBL (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE GAME_TBL
	ADD FOREIGN KEY (user_id1)
	REFERENCES USER_TBL (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE GAME_TBL
	ADD FOREIGN KEY (user_id2)
	REFERENCES USER_TBL (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SERIESE_POINT
	ADD FOREIGN KEY (user_id)
	REFERENCES USER_TBL (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SERIES_TBL
	ADD FOREIGN KEY (sub_organizer_id3)
	REFERENCES USER_TBL (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SERIES_TBL
	ADD FOREIGN KEY (sub_organizer_id1)
	REFERENCES USER_TBL (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SERIES_TBL
	ADD FOREIGN KEY (sub_organizer_id2)
	REFERENCES USER_TBL (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE SERIES_TBL
	ADD FOREIGN KEY (main_organizer_id)
	REFERENCES USER_TBL (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



