package com.jpa.demo.po;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "coursetype")
public class CourseTypePO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable=false)
    @JsonProperty("Id")
    private Integer id;

    @Column(name = "ProjectId")
    @JsonProperty("ProjectId")
    private Integer projectId;

    @Column(name = "Title")
    @JsonProperty("Title")
    private String title;

    @Column(name = "SortNumber")
    @JsonProperty("SortNumber")
    private Integer sortNumber;

    @JsonProperty("UpdateTime")
    private String updateTime;

    @JsonProperty("Content")
    private String content;

    @JsonProperty("ContentType")
    private String contentType;

    public CourseTypePO(Integer projectId, String title) {
        this.projectId = projectId;
        this.title = title;
    }

    public CourseTypePO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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
