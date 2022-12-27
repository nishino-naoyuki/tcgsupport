package com.tcgsupport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DaschboardController {

	@RequestMapping(value= {"dashboard","/"}, method=RequestMethod.GET)
	public ModelAndView dashboard(ModelAndView mv,@RequestParam(name="flg", required = false)Boolean flg) {
		
		if( flg != null ) {
			mv.addObject("flg", flg);
		}else {
			mv.addObject("flg", false);
		}
		mv.setViewName("dashboard");
		
		return mv;
	}
}
