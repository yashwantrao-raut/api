package com.api.domain;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "creationDateTime",nullable = false)
    private DateTime creationDateTime;

    @Column(name = "lastUpdateDateTime",nullable = false)
    private DateTime lastUpdateDateTime;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName="id" ,nullable = false)
    private Set<Note> notes = new HashSet<>(0);


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(DateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public DateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(DateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public User(Set<Note> notes, String name, String password, String email,DateTime creationDateTime) {
        this(notes,name,password,email,creationDateTime,DateTime.now());
    }

    public User(Set<Note> notes, String name, String password, String email, DateTime creationDateTime, DateTime lastUpdateDateTime) {
        this.notes = notes;
        this.name = name;
        this.password = password;
        this.email = email;
        this.creationDateTime=creationDateTime;
        this.lastUpdateDateTime=lastUpdateDateTime;
    }
    public User(){

    }
}
