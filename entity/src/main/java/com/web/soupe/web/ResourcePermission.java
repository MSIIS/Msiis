package com.web.soupe.web;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lenovo on 2015/7/3.
 */
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "soupe_res_permission", catalog = "apple")
public class ResourcePermission extends SimpleProperty implements Serializable {
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

    public ResourcePermission() {
    }

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
