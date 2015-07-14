package com.web.soupe.web;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2015/7/3.
 */
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "soupe_user", catalog = "apple")
public class User extends SimpleProperty implements Serializable {
    private static final long serialVersionUID = 2769404133329184773L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   /* @GenericGenerator(name = "persistenceGenerator", strategy = "increment")*/
    @Column(name = "user_id", nullable = false)
    private Long id;
    //登录名
    @Column(name = "user_name", nullable = false,unique = true)
    private String userName;
    //密码
    @Column(name = "password", nullable = false)
    private String password;

    //用户盐
    @Column(name="salt")
    private String salt ;
    //昵称
    @Column(name="nick_name",nullable = false,unique = true)
    private  String nickName ;
    //真实姓名
    @Column(name = "real_name",nullable = false)
    private String realName;

    @OneToOne(targetEntity = Organization.class)
    @JoinColumn(name = "org_id",referencedColumnName = "org_id")
    private Organization organization;

    @OneToMany(targetEntity = UserRoleOrgRelation.class,
            mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserRoleOrgRelation> userRoleOrgRelations;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRoleOrgRelation> getUserRoleOrgRelations() {
        return userRoleOrgRelations;
    }

    public void setUserRoleOrgRelations(List<UserRoleOrgRelation> userRoleOrgRelations) {
        this.userRoleOrgRelations = userRoleOrgRelations;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
