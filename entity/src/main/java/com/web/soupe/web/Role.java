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

@Table(name = "soupe_role", catalog = "apple")
public class Role implements Serializable {
    private static final long serialVersionUID = -4456599242112294410L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GenericGenerator(name = "persistenceGenerator", strategy = "increment")*/
    @Column(name = "role_id", nullable = false)
    private Long id;
    @Column(name = "role_code", nullable = false,unique = true)
    private String code;
    @Column(name = "role_name", nullable = false)
    private String name;
    /**
     * 角色类型  0，普通用户角色， 8 管理员角色 9 超级管理员角色
     */
    @Column(name = "role_type", nullable = false)
    private int type;

    // 0未激活 1正常
    @Column(name="status",nullable = false)
    private  int status = 0;
    /**
     * 是否系统预设角色
     */
    @Column(name = "is_system",nullable = false)
    private boolean isSystem=false;

    @OneToMany(targetEntity = UserRoleOrgRelation.class,
            mappedBy = "role",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<UserRoleOrgRelation> userRoleOrgRelations;

    @OneToMany(targetEntity = RolePermissionRelation.class,
            mappedBy = "role",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RolePermissionRelation> rolePermissionRelations;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    public List<UserRoleOrgRelation> getUserRoleOrgRelations() {
        return userRoleOrgRelations;
    }

    public void setUserRoleOrgRelations(List<UserRoleOrgRelation> userRoleOrgRelations) {
        this.userRoleOrgRelations = userRoleOrgRelations;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RolePermissionRelation> getRolePermissionRelations() {
        return rolePermissionRelations;
    }

    public void setRolePermissionRelations(List<RolePermissionRelation> rolePermissionRelations) {
        this.rolePermissionRelations = rolePermissionRelations;
    }
}
