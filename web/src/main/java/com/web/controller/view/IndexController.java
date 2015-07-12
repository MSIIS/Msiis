package com.web.controller.view;

import com.util.model.UrlPrefix;
import com.web.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lenovo on 2015/7/12.
 */
@Controller
@RequestMapping(value = IndexController.path)
public class IndexController extends BaseController {
    private  static  final Logger logger = LoggerFactory.getLogger(IndexController.class);
    protected  static  final String  path = UrlPrefix.FORE_PATH;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return   UrlPrefix.FORE_VIEW_PAGE+"index";
    }
    @RequestMapping(value="",method = RequestMethod.GET)
    public String toIndex(){
        return  UrlPrefix.FORE_VIEW_PAGE+ "index";
    }

}
