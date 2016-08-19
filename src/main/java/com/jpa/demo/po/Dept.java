package com.jpa.demo.po;

import javax.persistence.*;

/**
*
*
*@author  zhousd
*@date    2015/10/28
*@version latest
*/
@Entity
@Table(name = "tbl_dept")
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @OneToOne(mappedBy = "dept")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
