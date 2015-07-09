package com.test.demo;

import com.util.tools.JsonUtils;
import com.web.service.impl.ServiceManager;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by nlf on 2015-7-7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml","classpath*:spring-servlet.xml"})
public class BaseTestCase {


  @Resource
  protected ServiceManager serviceManager;

    public ServiceManager getServiceManager() {
        return serviceManager;
    }

    public void setServiceManager(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    public <T> void showInfoForCollection(Collection<T> coll) {
        for (T ob : coll) {
            System.out.println(JsonUtils.toJSONString(ob));
        }

    }


}
