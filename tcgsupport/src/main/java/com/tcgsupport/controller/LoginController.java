package com.tcgsupport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcgsupport.form.LoginForm;

@Controller
public class LoginController {

	@RequestMapping(value= {"login"}, method=RequestMethod.GET)
	public ModelAndView login(ModelAndView mv,@RequestParam(name="flg", required = false)Boolean flg) {
		
		if( flg != null ) {
			mv.addObject("flg", flg);
		}else {
			mv.addObject("flg", false);
		}
		mv.setViewName("login");
		
		return mv;
	}
	
	@RequestMapping(value= {"auth"}, method=RequestMethod.POST)
	public String auth(RedirectAttributes redirectAttributes,LoginForm form) {
		String url;
		
		url = "redirect:dashboard";
		
		return url;
	}
}
