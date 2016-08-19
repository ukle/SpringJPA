package com.jpa.demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerVO {

    @JsonProperty("Id")
    private Integer id;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("Age")
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
