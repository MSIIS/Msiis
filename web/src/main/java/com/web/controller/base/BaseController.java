package com.web.controller.base;

import com.web.service.impl.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2015/7/4.
 */
public  class BaseController {

    @Autowired
    protected ServiceManager serviceManager ;

    public ServiceManager getServiceManager() {

        return serviceManager;
    }

    public void setServiceManager(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }
}
