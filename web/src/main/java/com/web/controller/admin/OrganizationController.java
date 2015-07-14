package com.web.controller.admin;

import com.util.model.UrlPrefix;
import com.web.controller.base.BaseController;
import com.web.soupe.dto.SoupeWebModel;
import com.web.soupe.web.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by nlf on 2015-7-14.
 */
@Controller
@RequestMapping(value=OrganizationController.PATH)
public class OrganizationController extends BaseController {
    protected static  final  String PATH= UrlPrefix.ADMIN_PATH +"org/";
    private  static  final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @RequestMapping(value = "select/list")
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

}
