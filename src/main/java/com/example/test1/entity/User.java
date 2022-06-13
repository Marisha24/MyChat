package com.example.test1.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Data
@Entity
@Table(name = "usr", schema = "public")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private boolean active;
    @Column(name = "ip")
    private String IP;
    @Column(name = "data_last_visit")
    private LocalDateTime dataLastVisit;

@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//fetch - это параметр, который опр. как ланные будут погружаться относительно основной сущности
@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
@Enumerated(EnumType.STRING)


    private Set<Role> roles; //фдмин, юзер
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
