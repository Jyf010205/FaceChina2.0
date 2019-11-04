package com.mysonandme.pojo.sys;

import lombok.Data;

import java.util.Set;


@Data
public class User {
    private String id;
    private String userName;
    private String password;
    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;

    public User(String s, String wsl, String s1, Set<Role> roleSet) {
        this.id = s;
        this.userName = wsl;
        this.password = s1;
        this.roles = roleSet;
    }
    //省略set、get方法等.....
}














