package com.tcgsupport.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcgsupport.config.SystemConfig;
import com.tcgsupport.dto.InsertUserDto;
import com.tcgsupport.dto.LoginInfoDto;
import com.tcgsupport.dto.UserInfoDto;
import com.tcgsupport.entity.ProvUserEntity;
import com.tcgsupport.entity.UserTblEntity;
import com.tcgsupport.repository.ProvUserRepository;
import com.tcgsupport.repository.UserRepository;
import com.tcgsupport.util.Digest;
import com.tcgsupport.util.Exchange;
import com.tcgsupport.util.Token;

@Service
public class UserService extends ServiceBase {
	
	/**
	 * ログイン処理
	 * @param mail
	 * @param pass
	 * @return
	 */
	public LoginInfoDto login(String mail,String pass) {
		LoginInfoDto dto = null;
		
		String pwdHash = Digest.createPassword(mail, pass);
		
		UserTblEntity userEntity = userRepository.getUser(mail, pwdHash);
		
		if( userEntity != null ) {
			dto = getLoginInfoFrom(userEntity);
		}
		
		return dto;
	}
	
	/**
	 * IDを指定してユーザー情報を取得する
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfoDto get(Integer userId) {
		UserInfoDto dto = null;

		UserTblEntity userEntity = userRepository.getReferenceById(userId);
		if (userEntity != null) {
			dto = getUserInfoFrom(userEntity);
		}
		return dto;
	}
	/**
	 * メールアドレスを指定してユーザーが存在するかどうかをチェックする
	 * @param mail
	 * @return
	 */
	public boolean isExist(String mail) {
		
		UserTblEntity userEntity = userRepository.getUserByMil(mail);
		
		return (userEntity!=null);
	}
	public boolean isCertificated(String mail) {
		boolean certificated = false;

		UserTblEntity userEntity = userRepository.getUserByMil(mail);
		if( userEntity != null ) {
			certificated = (userEntity.getCertification()==UserTblEntity.CERTIFICATED);
		}
		return certificated;
	}

	/**
	 * ユーザー情報を挿入する
	 * 
	 * @param userInfo
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public UserInfoDto insertNew(InsertUserDto userInfo) {
		UserInfoDto dto = null;
	
		//ユーザーテーブルに挿入
		UserTblEntity userEntity = userRepository.save(getUserEntityFrom(userInfo));
		//仮テーブルに挿入
		provUserRepository.save(getProvUserEntityFrom(userInfo,userEntity.getUserId()));
		//メールを送信: TODO
		
		return dto;
	}
	
	
	
	/* --private methods-- */
	/**
	 * EntityからDTOを作成する
	 * 
	 * @param userEntity
	 * @return
	 */
	private LoginInfoDto getLoginInfoFrom(UserTblEntity userEntity) {
		LoginInfoDto dto = new LoginInfoDto();
		
		dto.setUserId(userEntity.getUserId());
		dto.setName(userEntity.getName());
		dto.setMail(userEntity.getMail());
		dto.setIcon(userEntity.getIcon());
		dto.setAdminflg( (userEntity.getAdminflg()==UserTblEntity.ADMIN?true:false) );
		dto.setCertification( (userEntity.getCertification()==UserTblEntity.CERTIFICATED?true:false) );
		
		return dto;
	}
	private UserInfoDto getUserInfoFrom(UserTblEntity userEntity) {
		UserInfoDto dto = new UserInfoDto();
		
		dto.setUserId(userEntity.getUserId());
		dto.setName(userEntity.getName());
		dto.setMail(userEntity.getMail());
		dto.setIcon(userEntity.getIcon());
		dto.setAdminflg( (userEntity.getAdminflg()==UserTblEntity.ADMIN?true:false) );
		dto.setCertification( (userEntity.getCertification()==UserTblEntity.CERTIFICATED?true:false) );
		
		return dto;
	}
	private UserTblEntity getUserEntityFrom(InsertUserDto dto) {
		UserTblEntity entity = new UserTblEntity();
		
		entity.setAdminflg(0);		//管理者は手動登録
		entity.setCertification(0);	//最初は未認証
		entity.setDescription("");
		entity.setIcon(null);
		entity.setMail(dto.getMail());
		entity.setName(dto.getName());
		entity.setPassword( Digest.createPassword(dto.getMail(), dto.getPassword()));
		
		return entity;
	}
	private ProvUserEntity getProvUserEntityFrom(InsertUserDto dto,Integer userId) {
		ProvUserEntity entity = new ProvUserEntity();
		
		//有効期限を設定
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime limitDate = now.plusMinutes( SystemConfig.getInstance().getProvlimit() );
		
		entity.setUserId(userId);
		entity.setMail(dto.getMail());
		entity.setToken( Token.getCsrfToken() );
		entity.setExpirationDate( Exchange.toDate(limitDate) );
		
		return entity;
	}
}
