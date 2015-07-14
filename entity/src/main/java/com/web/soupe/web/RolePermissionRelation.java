package com.web.soupe.web;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nlf on 2015-7-14.
 */
@Entity
@Table(name="soupe_role_permission",catalog = "apple")
public class RolePermissionRelation implements Serializable {
    private static final long serialVersionUID = 2171384358301503304L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;

    @ManyToOne(targetEntity = Role.class,fetch = FetchType.LAZY)
    @JoinColumn(name="role_id",referencedColumnName = "role_id")
    private Role role;
    @ManyToOne(targetEntity = Permission.class,fetch = FetchType.LAZY)
    @JoinColumn(name="res_id",referencedColumnName = "res_id")
    private Permission permission;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
