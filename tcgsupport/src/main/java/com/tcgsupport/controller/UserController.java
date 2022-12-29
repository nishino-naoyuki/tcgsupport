package com.tcgsupport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	/**
	 * 仮登録から本登録へ
	 * @param mv
	 * @param token
	 * @param userid
	 * @return
	 */
	@RequestMapping(value= {"provuser/{token}"}, method=RequestMethod.GET)
	public ModelAndView provAuth(
			ModelAndView mv,
			@PathVariable("token") String token,
			@RequestParam(name="userid", required = false)Integer userid) {
		
		return mv;
	}
}
