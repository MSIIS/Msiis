package com.web.security.realm;

import com.util.config.UserConfig;
import com.web.service.UserService;
import com.web.soupe.web.Role;
import com.web.soupe.web.User;
import com.web.soupe.web.UserRoleRelation;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.relation.Relation;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

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
        String name = (String) getAvailablePrincipal(principals);
        List<String> roles = new ArrayList<String>();
        User user =userService.findUserByNameAndPassword(name,"",1);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<UserRoleRelation> relations =user.getUserRoleRelationList();
        for(UserRoleRelation relation :relations){
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
        String password =String.valueOf(token.getPassword());
         User user =userService.findUserByNameAndPassword(userName,password,1);
        if (user == null) {
            throw new AuthenticationException();
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),user.getRealName());
        this.setSession(UserConfig.USER_LOGON_SESSION.getCode(),user);
        return info;
    }
    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     *
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}