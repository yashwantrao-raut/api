package com.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title" ,nullable = false)
    private String title;
    @Column(name = "text",nullable = false)
    private String text;

    @Column(name = "createTime",nullable = false)
    private DateTime createTime;

    @Column(name = "lastUpdateTime",nullable = false)
    private DateTime lastUpdateTime;

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName="id",insertable=false, updatable=false)
    private User user;

    public Note(String title, String text,DateTime createTime,DateTime lastUpdateTime) {
        this.title = title;
        this.text = text;
        this.createTime=createTime;
        this.lastUpdateTime=lastUpdateTime;
    }

    public Note(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public DateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(DateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
