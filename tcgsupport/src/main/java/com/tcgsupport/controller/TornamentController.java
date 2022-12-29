package com.tcgsupport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tcgsupport.dto.InsertUserDto;
import com.tcgsupport.form.EntryTornamentForm;
import com.tcgsupport.form.RegisterTornamentInfoForm;
import com.tcgsupport.service.UserService;

@Controller
public class TornamentController {

	@Autowired
	UserService userService;
	
	/**
	 * 大会参加者登録
	 * @param mv
	 * @param form
	 * @return
	 */
	@RequestMapping(value= {"torn/entry"}, method=RequestMethod.GET)
	public ModelAndView entry(ModelAndView mv,EntryTornamentForm form) {
		//既に登録があるか？
		if( userService.isExist(form.getMail())) {
			//既に登録がある場合は、まだ本登録がまだなら、メールを再送する
			if( userService.isCertificated(form.getMail())) {
				//メール再送信：TODO
			}
		}else {
			//新規
			userService.insertNew( getInsertUserDto(form) );
		}
		return mv;
	}
	
	@RequestMapping(value= {"torn/disp"}, method=RequestMethod.GET)
	public ModelAndView dispay(ModelAndView mv) {
		mv.setViewName("torn_register");
		return mv;
	}
	
	public ModelAndView register(
			ModelAndView mv,RegisterTornamentInfoForm form) {
		
		return mv;
	}
	
	/*--private method--*/
	/**
	 * 新規挿入用のデータを作成する
	 * 
	 * @param form
	 * @return
	 */
	private InsertUserDto getInsertUserDto(EntryTornamentForm form) {
		InsertUserDto dto = new InsertUserDto();
		
		dto.setMail(form.getMail());
		dto.setName(form.getName());
		dto.setPassword("");	//大会登録からの場合は、後でパスワードを設定させる
		
		return dto;
	}
}
