package com.web.security.cache;

import com.util.tools.SpringBeanUtils;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by nlf on 2015-7-13.
 */
public class CacheManageUtils {
    private static CacheManager cacheManager =null;

    public static CacheManager getManagerInstance(){
        if(cacheManager==null){
            cacheManager=(SpringCacheManagerWrapper)SpringBeanUtils.getBean("myCacheManager");
        }
        return cacheManager;
    }
}
