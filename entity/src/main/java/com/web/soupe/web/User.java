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
@Table(name = "soupe_user", catalog = "apple")
public class User implements Serializable {
    private static final long serialVersionUID = 2769404133329184773L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   /* @GenericGenerator(name = "persistenceGenerator", strategy = "increment")*/
    @Column(name = "user_id", nullable = false)
    private Long id;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "passsord", nullable = false)
    private String password;

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
}
