package com.web.controller.view;

import com.util.model.UrlPrefix;
import com.web.controller.base.BaseController;
import com.web.service.OrganizationService;
import com.web.service.UserService;
import com.web.soupe.dto.SoupeConst;
import com.web.soupe.dto.SoupeWebModel;
import com.web.soupe.web.Organization;
import com.web.soupe.web.User;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by nlf on 2015-7-14.
 */
@Controller
@RequestMapping(UserRegesterController.path)
public class UserRegesterController extends BaseController {
    private static final Logger logger = Logger.getLogger(UserRegesterController.class);
    protected static final String path = UrlPrefix.FORE_PATH + "/user/";

    @RequestMapping(value = "/register/submit", method = RequestMethod.POST)
    @ResponseBody
    public SoupeWebModel userRegister(
            @RequestParam(value = "userName",required = true) String username,
            @RequestParam(value = "password",required = true) String password,
            @RequestParam(value = "salt",required = true) String salt,
            @RequestParam(value = "nickName",required = true) String nickName,
            @RequestParam(value = "realName",required = true) String realName,
            @RequestParam(value = "orgId",required = true) Integer orgId ){
        SoupeWebModel soupeWebModel = new SoupeWebModel();
        try{
            UserService userService=this.getServiceManager().getUserService();
            OrganizationService organizationService=this.getServiceManager().getOrganizationService();
            if(!userService.checkExists(username,nickName)){
                if(orgId>0){
                   Organization organization= organizationService.find(orgId);
                   User user =new User();
                   user.setOrganization(organization);
                    user.setNickName(nickName);
                    user.setRealName(realName);
                    user.setUserName(username);
                    user.setPassword(password);
                    user.setSalt(salt);
                    userService.save(user);
                    soupeWebModel.setSuccess(true);
                }
            }else{
                soupeWebModel.setMessage("用户名或者昵称已经存在。");
            }
        }catch(Exception ex){
            logger.error("用户注册发生异常。" + ex.getMessage());
            soupeWebModel.setMessage("用户注册发生异常。");
        }
        return soupeWebModel;
    }

    @RequestMapping(value = "reg/orgs",method = RequestMethod.GET)
    public SoupeWebModel getOrgChildrenList(
            @RequestParam(value = "parent_id") Integer parendId){
        SoupeWebModel soupeWebModel=new SoupeWebModel();
        try{
            if(parendId==null||parendId<0){
                parendId=0;
            }
            List<Organization> organizations =this.getServiceManager().
                    getOrganizationService().getAllList(parendId);
            soupeWebModel.setData(organizations);
            soupeWebModel.setSuccess(true);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return soupeWebModel;
    }

    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String turnToRegister(Model model){
        List<Organization> organizations =null;
        try{
           organizations=this.getServiceManager().getOrganizationService().getAllList(0);

        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        model.addAttribute("selectOrgs",organizations);
        return  UrlPrefix.FORE_VIEW_PAGE+"register";
    }
}
