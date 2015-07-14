package com.web.controller.admin;

import com.util.model.UrlPrefix;
import com.web.controller.base.BaseController;
import com.web.service.OrganizationService;
import com.web.service.UserService;
import com.web.soupe.dto.SoupeConst;
import com.web.soupe.dto.SoupeWebModel;
import com.web.soupe.util.TreeEntityUtils;
import com.web.soupe.web.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * b保存
     * @param orgName
     * @param orgCode
     * @param parentId
     * @param orgName
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public SoupeWebModel save(
            @RequestParam(value = "orgName")String orgName,
            @RequestParam(value = "orgCode")String orgCode,
            @RequestParam(value = "parentId")Integer parentId
            ){
        SoupeWebModel soupeWebModel = new SoupeWebModel();
        try{
           Organization o = new Organization();
            o.setOrgCode(orgCode);
            o.setOrgName(orgName);
            o.setLeaf(true);
            o.setDeleted(false);
            o.setStatus(SoupeConst.STATUS_OK);
            o.setParentId(parentId);
            OrganizationService organizationService = this.getServiceManager().getOrganizationService();
            organizationService.save(o);
            List<Organization> organizations = organizationService.getListForSort();
            organizations= (List<Organization>) TreeEntityUtils.sortTreeEntity(organizations);
            organizationService.saveAll(organizations);
            soupeWebModel.setSuccess(true);
        }catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            soupeWebModel.setSuccess(false);
        }
        return soupeWebModel;
    }


}
