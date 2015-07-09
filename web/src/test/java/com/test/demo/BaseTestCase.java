package com.test.demo;

import com.util.tools.JSonUtils;
import com.web.service.impl.ServiceManager;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by nlf on 2015-7-7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml","classpath*:spring-servlet.xml"})
/*@TransactionConfiguration(transactionManager = "transactionManager")*/
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
            System.out.println(JSonUtils.toJSONString(ob));
        }

    }


}