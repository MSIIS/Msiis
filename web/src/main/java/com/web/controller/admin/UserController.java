package com.web.controller.admin;

import com.util.model.PageInfo;
import com.util.model.UrlPrefix;
import com.web.controller.base.BaseController;
import com.web.soupe.web.User;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2015/7/11.
 */
@Controller
@RequestMapping(value = UserController.PATH)
public class UserController extends BaseController {
    protected static  final  String PATH= UrlPrefix.ADMIN_PATH +"user/";
    private  static  final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequiresUser
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String getUserList(
            @RequestParam(value = "userName",required = false) String userName,
            @RequestParam(value = "pageNo",required = false) String pageNo,
            @RequestParam(value = "pageSize",required = false) String pageSize,
            Model model){
        Map<String,String> paramMap=new HashMap<String,String>();
        try {
            paramMap.put("pageNo",pageNo);
            paramMap.put("pageSize",pageSize);
            paramMap.put("userName",userName);
            PageInfo<User> users=this.getServiceManager().getUserService().findPageInfoRule(paramMap);
            model.addAttribute("users",users);
        }catch (Exception ex){
            logger.error(ex.getMessage(),ex);
        }
        return "admin/user-list";
    }
}
