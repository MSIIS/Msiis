package com.util.tools;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <h3>spring 工具类</h3>
 * 
 * @author liangzh
 *
 * @since  common-0.0.1-SNAPSHOT
 * 
 * 2013年8月29日 下午3:15:46
 *
 */
public class SpringBeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    /**
     * <b>获取spring的注入bean</b>
     * 
     * @param name bean名称
     * 
     * @return Object
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
}
