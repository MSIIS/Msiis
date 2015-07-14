package com.web.soupe.web;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2015/7/3.
 */
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "soupe_permission", catalog = "apple")
public class Permission extends SimpleProperty implements Serializable,TreeEntity {
    private static final long serialVersionUID = -8662431340218255008L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   /* @GenericGenerator(name = "persistenceGenerator", strategy = "increment")*/
    @Column(name = "res_id", nullable = false)
    private int id;
    @Column(name = "res_path", nullable = false)
    private String resourcePath;
    @Column(name = "description")
    private String description;
    @Column(name = "parent_id")
    private int parentId;
    @Column(name="res_name")
    private String  res_name;
    @Column(name="sort_code")
    private String sortCode;
    @Column(name="is_leaf")
    private  boolean isLeaf=false;
    @OneToMany(targetEntity = RolePermissionRelation.class,
            mappedBy = "permission",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RolePermissionRelation> rolePermissionRelations;

    public Permission() {
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public int getParentId() {
        return parentId;
    }
    @Override
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public List<RolePermissionRelation> getRolePermissionRelations() {
        return rolePermissionRelations;
    }

    public void setRolePermissionRelations(List<RolePermissionRelation> rolePermissionRelations) {
        this.rolePermissionRelations = rolePermissionRelations;
    }

    @Override
    public String getSortCode() {
        return this.sortCode;
    }

    @Override
    public boolean isLeaf() {
        return this.isLeaf;
    }

    @Override
    public void setLeaf(boolean b) {
     this.isLeaf=b;
    }

    @Override
    public void setSortCode(String sortCode) {
      this.sortCode=sortCode;
    }
}
