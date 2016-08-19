package com.jpa.demo.po;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name="JPA_CUTOMERS")
@Entity
public class Customer {

    private Integer id;
    private String lastName;
    private String email;
    private int age;

    private Set<Order> orders = new HashSet<Order>();

    //映射单向 1-n 的关联关系
    //使用 @OneToMany 来映射 1-n 的关联关系
    //使用 @JoinColumn 来映射外键列的名称
    //可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
    //可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略.
    //注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用 @JoinColumn 属性了.
    @OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REMOVE},mappedBy="customer")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="LAST_NAME",length=50,nullable=false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
