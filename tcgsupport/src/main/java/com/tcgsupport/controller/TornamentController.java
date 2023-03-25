package com.tcgsupport.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tcgsupport.dto.InsertUserDto;
import com.tcgsupport.dto.TornRegisterConfirmDto;
import com.tcgsupport.form.EntryTornamentForm;
import com.tcgsupport.form.RegisterTornamentInfoForm;
import com.tcgsupport.form.SeriesForm;
import com.tcgsupport.param.LocalEnum;
import com.tcgsupport.param.MethodEnum;
import com.tcgsupport.param.RegiTypeEnum;
import com.tcgsupport.param.RegulationEnum;
import com.tcgsupport.param.SessionConst;
import com.tcgsupport.service.SeriesService;
import com.tcgsupport.service.TornService;
import com.tcgsupport.service.UserService;
import com.tcgsupport.util.Exchange;
import com.tcgsupport.util.FileUtils;

@Controller
public class TornamentController {
	private static final Logger logger = LoggerFactory.getLogger(TornamentController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	TornService tornService;
	
	@Autowired
	SeriesService seriesService;
	
	@Autowired
	HttpSession session;
	
	/**
	 * 大会参加者登録
	 * @param mv
	 * @param form
	 * @return
	 */
	@RequestMapping(value= {"/torn/entry"}, method=RequestMethod.GET)
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
	
	/**
	 * 登録画面表示
	 * @param mv
	 * @param registerTornamentInfoForm
	 * @return
	 */
	@RequestMapping(value= {"/torn/register"}, method=RequestMethod.GET)
	public ModelAndView dispay(ModelAndView mv,RegisterTornamentInfoForm registerTornamentInfoForm) {
		mv.setViewName("torn_register");
		return mv;
	}

	/**
	 * 登録確認画面
	 * @param mv
	 * @param registerTornamentInfoForm
	 * @param error
	 * @return
	 */
	@RequestMapping(value= {"/torn/confirm"}, method=RequestMethod.POST)
	public ModelAndView confirm(ModelAndView mv,
			@Validated RegisterTornamentInfoForm registerTornamentInfoForm,
			BindingResult error) {
		//エラーがある場合は入力画面へ戻る
		if( error.hasErrors() ) {
			mv.setViewName("torn_register");
		}else {			
			TornRegisterConfirmDto dto = getTornRegisterConfirmDto(registerTornamentInfoForm);
			//セッションにデータ保存
			session.setAttribute(SessionConst.TORNDATA, dto);
			//DTOにデータコピー
			mv.addObject("tornConfirm",dto);
			
			mv.setViewName("torn_register_confirm");
		}
		return mv;
	}
	
	/**
	 * 登録処理
	 * @param mv
	 * @return
	 */
	@RequestMapping(value= {"/torn/register"}, method=RequestMethod.POST)
	public String register(
			ModelAndView mv) {
		String resultMsg = "登録に失敗しました";
		
		logger.info("torn/register!!!");
		//セッションから情報取得
		TornRegisterConfirmDto dto = (TornRegisterConfirmDto)session.getAttribute(SessionConst.TORNDATA);
		if(dto != null) {
			tornService.insert(dto, null);
			resultMsg = "登録に成功しました";
		}
		
		return resultMsg;
	}

	@RequestMapping(value = { "/series/register" }, method = RequestMethod.POST)
	@ResponseBody
	public Object registerSeriese(
			SeriesForm seriesForm,
			ModelAndView mv) {
		
		//既に存在するタイトルか？
		if( seriesService.isExist(seriesForm.getTitle())) {
			return "alreadyexist";
		}
	
		seriesService.insert(seriesForm);
		
		return "OK";
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
		
		if( form.getSeriesId() == null || form.getSeriesId() ==-1) {
			dto.setSeriesId(null);
			dto.setSeriesName("指定なし");
		}else {
			dto.setSeriesId(form.getSeriesId());
		}
		dto.setCapacity(form.getCapacity());
		dto.setDescription(form.getDescription());
		if( StringUtils.isEmpty(form.getIcon().getOriginalFilename()) ) {
			dto.setIcon("default.png");
		}else {
			//アイコンファイルをアップロード
			String fname = FileUtils.uploadIconFile( form.getIcon() );
			dto.setIcon(fname);
		}
		dto.setLocalId(form.getLocalId());
		dto.setLocalName(LocalEnum.getBy(form.getLocalId()).getName());
		dto.setMethodId(form.getMethod());
		dto.setMethod(MethodEnum.getBy(form.getMethod()).getName());
		dto.setRegisterTypId(form.getRegisterTyp());
		dto.setRegisterTyp(RegiTypeEnum.getBy(form.getRegisterTyp()).getName());
		dto.setRegulationId(form.getRegulation());
		dto.setRegulation(RegulationEnum.getBy(form.getRegulation()).getName());
		dto.setName(form.getName());
		//デッキ締め切り
		dto.setDeckLimit(form.getDeckLimit());
		dto.setDeckLimitDsp(Exchange.toFormatString(form.getDeckLimit(), "yyyy/MM/dd hh:mm:ss","指定なし"));
		//開催日
		dto.setEventDate(form.getEventDate());
		dto.setEventDateDsp(Exchange.toFormatString(form.getEventDate(), "yyyy/MM/dd"));
		//情報公開開始
		dto.setPublicstart(form.getPublicstart());
		dto.setPublicstartDsp(Exchange.toFormatString(form.getPublicstart(), "yyyy/MM/dd hh:mm:ss","登録と同時"));
		//募集開始
		dto.setEntryStartTime(form.getEntryStartTime());
		dto.setEntryStartTimeDsp(Exchange.toFormatString(form.getEntryStartTime(), "yyyy/MM/dd hh:mm:ss","公開と同時"));
		//募集終了
		dto.setEntryEndTime(form.getEntryEndTime());
		dto.setEntryEndTimeDsp(Exchange.toFormatString(form.getEntryEndTime(), "yyyy/MM/dd hh:mm:ss","指定なし"));
		
		
		return dto;
	}
}
