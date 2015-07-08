package com.test.demo;

import com.util.model.PageInfo;
import com.web.service.SsqService;
import com.web.soupe.web.User;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void int2Char(){
        System.out.println((char)63);
        Map<String,String> map = new HashMap<String,String>();
        map.put("pageNo",2+"");
        map.put("pageSize",3+"");
        PageInfo<User>  pageInfo =this.getServiceManager().getUserService().findPageInfoRule(map);
        System.out.println(pageInfo);
    }

    @Test
    public void getMax(){
        System.out.println(Long.MAX_VALUE+":"+Long.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE+":"+Integer.MIN_VALUE);
    }
}
