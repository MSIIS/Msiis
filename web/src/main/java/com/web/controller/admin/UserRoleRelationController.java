package com.web.controller.admin;

import com.util.model.UrlAccessPrefix;
import com.web.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lenovo on 2015/7/11.
 */
@Controller
@RequestMapping(value = UserRoleRelationController.path)
public class UserRoleRelationController  extends BaseController {
    private static  final Logger logger = LoggerFactory.getLogger(UserRoleRelationController.class);
    protected  static  final  String path = UrlAccessPrefix.BACK_STAGE_PATH+"user/";

    @RequestMapping(value ="add",method = RequestMethod.POST)
    public ModelAndView setUserRoleRelation(HttpServletRequest request){
        try{

        }catch (Exception ex){

        }
        return null;
    }


}
