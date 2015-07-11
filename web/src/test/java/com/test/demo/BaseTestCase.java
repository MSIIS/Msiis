package com.test.demo;

import com.util.tools.JSonUtils;
import com.web.service.impl.ServiceManager;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Junit测试基类
 * 懒加载的时候，必须增加事物控制，否则事物session 提前关闭，拿不到数据。
 * Created by nlf on 2015-7-7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml","classpath*:spring-servlet.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = false)
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
