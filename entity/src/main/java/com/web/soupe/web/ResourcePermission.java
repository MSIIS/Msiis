package com.web.soupe.web;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lenovo on 2015/7/3.
 */
@Entity
@Table(name = "soupe_res_permission")
public class ResourcePermission  extends SimpleProperty implements Serializable {
    private static final long serialVersionUID = -8662431340218255008L;

    @Id
    @Column(name="res_id",nullable = false)
    private  int id ;
    @Column(name = "res_path",nullable = false)
    private String resourcePath;
    @Column(name = "description")
    private  String description ;
    @Column(name = "parent_id")
    private  int parentId ;

    public int getId() {
        return id;
    }

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
