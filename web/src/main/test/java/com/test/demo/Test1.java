package com.test.demo;

import com.util.tools.AssertUtils;
import com.web.service.SsqService;
import org.junit.Test;

/**
 * Created by nlf on 2015-7-7.
 */
public class Test1 extends  BaseTestCase {

    @Test
    public void  rollServiceTest(){
        SsqService ssqService =this.getServiceManager().getSsqService();
        System.out.println(ssqService.findNums("1", "kill"));
        System.out.println(ssqService);

    }

}
