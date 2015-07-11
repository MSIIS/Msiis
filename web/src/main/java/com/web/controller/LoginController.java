package com.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.util.config.UserConfig;
import com.util.model.UrlAccessPrefix;
import org.apache.log4j.Logger;
import com.web.soupe.web.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = LoginController.PATH)
public class LoginController extends BaseController {
	protected static final String PATH = UrlAccessPrefix.BACK_STAGE_PATH;
	private Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
        System.out.printf("login.jsp....");
        return new ModelAndView("login", "message", null);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView login1(HttpServletRequest request) {
        System.out.println("check login ....");
        User user = (User) request.getSession().getAttribute(UserConfig.USER_LOGON_SESSION.getCode());
		if(user==null){
			return new ModelAndView("login", "message", null);
		}
		return new ModelAndView("success", "message", null);
	}

	@RequestMapping(value = "login/submit", method = {RequestMethod.POST})
	public ModelAndView submit(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "password", required = true) String password,
			HttpServletRequest request) {
		Map<String, String> model = new HashMap<String, String>();
		try {
			User user = this.serviceManager.getUserService().findUserByNameAndPassword(name, password);
			if (user != null) {
				request.getSession().setAttribute(UserConfig.USER_LOGON_SESSION.getCode(), user);
				model.put("msg", "login success!");
			} else {
				model.put("msg", "failure!");
				return new ModelAndView("login", model);
			}

		} catch (Exception ex) {
			logger.error("验证登录信息出错，" + ex.getMessage(), ex);
		}
		return new ModelAndView("success", model);
	}

}
