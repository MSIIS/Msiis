package com.web.security.realm;

import com.web.service.UserService;
import com.web.soupe.web.User;
import com.web.soupe.web.UserRoleOrgRelation;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm  implements CacheManagerAware{

    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("myCacheManager")
    private CacheManager cacheManager;


    /*
     *获取了当前登录用户的角色信息。
     *
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        /*User user = (User)principals.fromRealm(getName()).iterator().next();*/
        String name = (String) getAvailablePrincipal(principals);
        Set<String> roles = new HashSet<String>();
        User user =userService.findUserByNameAndPassword(name,"",1);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<UserRoleOrgRelation> relations =user.getUserRoleOrgRelations();
        for(UserRoleOrgRelation relation :relations){
            roles.add(String.valueOf(relation.getRole().getId()));
        }
        info.addRoles(roles);
        return info;
    }

    /*
     * (non-Javadoc)
     *  认证是否通过,登录
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName=token.getUsername();
         User user =userService.findUserByNameAndPassword(userName,"",1);
        if (user == null) {
            throw new AuthenticationException();
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt())
                ,user.getRealName());
        return info;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

}