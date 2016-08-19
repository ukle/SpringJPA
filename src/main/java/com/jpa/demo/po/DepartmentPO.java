package com.jpa.demo.po;

import javax.persistence.*;
import java.util.Date;

@Entity//指出该Java 类为实体类，将映射到指定的数据库表
@Table(name = "department")//当实体类与其映射的数据库表名不同名时需要使用 @Table 标注说明
public class DepartmentPO {

    /**
     * Id 标注用于声明一个实体类的属性映射为数据库的主键列;Id标注也可置于属性的getter方法之前
     */
    @Id
    /**
     * GeneratedValue 用于标注主键的生成策略
     * 默认情况下，JPA 自动选择一个最适合底层数据库的主键生成策略：SqlServer 对应 identity，MySQL 对应 auto。
     * IDENTITY：采用数据库 ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
     * AUTO：JPA自动选择合适的策略，是默认选项
     * SEQUENCE：通过序列产生主键，通过 @SequenceGenerator 注解指定序列名，MySql 不支持这种方式
     * TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * 当实体的属性与其映射的数据库表的列不同名时需要使用@Column 标注说明
     * @Column 标注的常用属性是 name，用于设置映射数据库表的列名。
     * name:字段的名称,默认与属性名称一致
     * nullable:是否允许为null,默认为true
     * unique:是否唯一,默认为false
     * length:字段的长度,仅对String类型的字段有效
     * columnDefinition:表示该字段在数据库中的实际类型
     */
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "Name", nullable = false)
    private String name;

    /**
     * 表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性.
     */
    @Transient
    private String except;

    /**
     * 在核心的 Java API 中并没有定义 Date 类型的精度(temporal precision).
     * 而在数据库中,表示 Date 类型的数据有 DATE, TIME, 和 TIMESTAMP 三种精度(即单纯的日期,时间,或者两者 兼备).
     * 在进行属性映射时可使用@Temporal注解来调整精度
     */
    @Temporal(TemporalType.TIME)
    private Date createDate;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "Content", length = 10000)
    private String content;

    private String contentType;

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

    public String getExcept() {
        return except;
    }

    public void setExcept(String except) {
        this.except = except;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
