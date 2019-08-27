package com.example.keyMy.domain;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private Integer age;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserAccount account;

    public UserAccount getAccount() {
        return account;
    }
    public String getAccountName(){
        return account != null ? account.getUsername() : "<none>";
    }


    public User() {
    }

    public User(String name, String email, Integer age, UserAccount account) {
        this.account = account;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

