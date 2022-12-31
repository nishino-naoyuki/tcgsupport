package com.tcgsupport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tcgsupport.dto.InsertUserDto;
import com.tcgsupport.dto.TornRegisterConfirmDto;
import com.tcgsupport.form.EntryTornamentForm;
import com.tcgsupport.form.RegisterTornamentInfoForm;
import com.tcgsupport.param.LocalEnum;
import com.tcgsupport.param.MethodEnum;
import com.tcgsupport.param.RegiTypeEnum;
import com.tcgsupport.param.RegulationEnum;
import com.tcgsupport.service.UserService;
import com.tcgsupport.util.Exchange;

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
	public ModelAndView dispay(ModelAndView mv,RegisterTornamentInfoForm registerTornamentInfoForm) {
		mv.setViewName("torn_register");
		return mv;
	}

	@RequestMapping(value= {"torn/confirm"}, method=RequestMethod.POST)
	public ModelAndView confirm(ModelAndView mv,
			@Validated RegisterTornamentInfoForm registerTornamentInfoForm,
			BindingResult error) {
		//エラーがある場合は入力画面へ戻る
		if( error.hasErrors() ) {
			mv.setViewName("torn_register");
		}else {
			//セッションにデータ保存
			//DTOにデータコピー
			mv.addObject("tornConfirm",getTornRegisterConfirmDto(registerTornamentInfoForm));
			
			mv.setViewName("torn_register_confirm");
		}
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
	private TornRegisterConfirmDto getTornRegisterConfirmDto(RegisterTornamentInfoForm form) {
		TornRegisterConfirmDto dto = new TornRegisterConfirmDto();
		
		if( form.getSeriesId() == null ) {
			dto.setSeriesName("");
		}else {
			
		}
		dto.setCapacity(form.getCapacity());
		dto.setDescription(form.getDescription());
		if( form.getIcon() == null ) {
			dto.setIcon("default.png");
		}else {
			dto.setIcon(form.getIcon().getOriginalFilename());
		}
		dto.setLocalName(LocalEnum.getBy(form.getLocalId()).getName());
		dto.setMethod(MethodEnum.getBy(form.getMethod()).getName());
		dto.setRegisterTyp(RegiTypeEnum.getBy(form.getRegisterTyp()).getName());
		dto.setRegulation(RegulationEnum.getBy(form.getRegulation()).getName());
		dto.setName(form.getName());
		//デッキ締め切り
		dto.setDeckLimit(Exchange.toFormatString(form.getDeckLimit(), "yyyy/MM/dd hh:mm:ss"));
		//開催日
		dto.setEventDate(Exchange.toFormatString(form.getEventDate(), "yyyy/MM/dd"));
		//情報公開開始
		dto.setPublicstart(Exchange.toFormatString(form.getPublicstart(), "yyyy/MM/dd hh:mm:ss"));
		//募集開始
		dto.setEntryStartTime(Exchange.toFormatString(form.getEntryStartTime(), "yyyy/MM/dd hh:mm:ss"));
		//募集終了
		dto.setEntryEndTime(Exchange.toFormatString(form.getEntryEndTime(), "yyyy/MM/dd hh:mm:ss"));
		
		
		return dto;
	}
}
