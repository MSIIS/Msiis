package com.web.soupe.web;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by nlf on 2015-7-14.
 */
@Entity
@Table(name="soupe_organizatioin",catalog = "apple")
public class Organization  extends SimpleProperty implements Serializable ,TreeEntity{
    private static final long serialVersionUID = 9153891663335252189L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "org_id")
    private  int id;

    @Column(name="org_name",nullable = false)
    private String orgName;

    @Column(name="ogr_code",nullable = false,unique = true)
    private  String orgCode;

    @Column(name="parent_id" ,nullable = false,columnDefinition = "int(11) DEFAULT 0 COMMENT 'parent id'")
    private int parentId = 0;

    @Column(name="is_leaf",nullable = false)
    private  boolean isLeaf=true;

    @Column(name="sort_code")
    private String sortCode;

    @OneToMany(targetEntity = User.class,fetch = FetchType.LAZY,mappedBy = "organization",cascade = CascadeType.ALL)
    private List<User> userList;

    public Organization() {
    }

    public Organization(String orgName, String orgCode, int parentId) {
        this.orgName = orgName;
        this.orgCode = orgCode;
        this.parentId = parentId;
    }

    public Organization(String orgName, String orgCode, int parentId, boolean isLeaf) {
        this.orgName = orgName;
        this.orgCode = orgCode;
        this.parentId = parentId;
        this.isLeaf = isLeaf;
    }

    @Override
    public int getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean isLeaf() {
        return isLeaf;
    }
    @Override
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }



    @Override
    public String getSortCode() {
        return this.sortCode;
    }

    @Override
    public void setSortCode(String sortCode) {
      this.sortCode=sortCode;
    }
}
