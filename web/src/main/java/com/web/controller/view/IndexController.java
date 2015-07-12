package com.web.controller.view;

import com.util.model.UrlAccessPrefix;
import com.web.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.print.DocFlavor;

/**
 * Created by lenovo on 2015/7/12.
 */
@Controller
public class IndexController extends BaseController {
    private  static  final Logger logger = LoggerFactory.getLogger(IndexController.class);
    protected  static  final String  path = UrlAccessPrefix.FORE_GROUD_PATH;

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return   "index";
    }
    @RequestMapping(method = RequestMethod.GET)
    public String toindex(){
        return   "index";
    }

}
