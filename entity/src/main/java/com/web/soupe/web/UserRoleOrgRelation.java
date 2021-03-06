package com.web.soupe.web;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户-角色关系-授权组织表表
 * Created by lenovo on 2015/7/9.
 */
@Entity
@Table(name="soupe_user_role_org",catalog = "apple")
public class UserRoleOrgRelation  implements Serializable{

    private static final long serialVersionUID = -6870397565109005129L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id ;

    @ManyToOne(targetEntity = User.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false,referencedColumnName = "user_id")
    private User user;

    @ManyToOne(targetEntity = Role.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",nullable = false,referencedColumnName = "role_id")
    private Role role ;

    /**
     * 用户被授予权限的组织
     */
    @Column(name="org_id",nullable = false)
    private int orgId=0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
}
