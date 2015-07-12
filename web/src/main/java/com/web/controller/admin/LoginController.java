package com.web.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.util.config.UserConfig;
import com.util.model.UrlAccessPrefix;
import com.util.myException.MyAuthencationException;
import com.web.controller.base.BaseController;
import org.apache.log4j.Logger;
import com.web.soupe.web.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = LoginController.PATH)
public class LoginController extends BaseController {
    protected static final String PATH = "/admin/";
    private Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        return new ModelAndView("admin/login", "message", null);
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView login1(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConfig.USER_LOGON_SESSION.getCode());
        if (user == null) {
            return new ModelAndView("admin/login", "message", null);
        }
        return new ModelAndView("admin/index", "message", null);
    }

    @RequestMapping(value = "login/submit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView submit(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "password", required = true) String password,
            HttpServletRequest request) {
        Map<String, String> model = new HashMap<String, String>();
        UsernamePasswordToken token = null;
        try {
            User user = (User) request.getSession().getAttribute(UserConfig.USER_LOGON_SESSION.getCode());
            if (user == null) {
                Subject currentUser = SecurityUtils.getSubject();
                token = new UsernamePasswordToken(name, password);
                token.setRememberMe(false);
                currentUser.login(token);
            }
        } catch (AuthenticationException a) {
            token.clear();
            return new ModelAndView("admin/login", "message", null);
        } catch (Exception ex) {
            logger.error("验证登录信息出错，" + ex.getMessage(), ex);
        }
        return new ModelAndView("admin/index", model);
    }

    @RequestMapping(value = "logout")
    public String logout(){
        Subject subject =SecurityUtils.getSubject();
        subject.logout();
        return "admin/login";
    }

    @RequestMapping(value = "error")
    public String error(){
        Subject subject =SecurityUtils.getSubject();
        subject.logout();
        return "admin/error";
    }


}
