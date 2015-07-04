package com.web.soupe.web;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lenovo on 2015/7/3.
 */
@Entity
@Table(name="soupe_role")
public class Role implements Serializable {
    private static final long serialVersionUID = -4456599242112294410L;
    @Id
    @Column(name = "role_id" ,nullable = false)
    private Long id ;
    @Column(name = "code" ,nullable = false)
    private String code ;
    @Column(name="role_name" ,nullable = false)
    private String name ;
    @Column(name = "role_type" ,nullable = false)
    private  String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
