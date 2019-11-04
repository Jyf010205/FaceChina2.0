package com.mysonandme.pojo.sys;

import lombok.Data;

import java.util.Set;


@Data
public class Role {

    private String id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permissions> permissions;
    //省略set、get方法等.....


    public Role(String id, String roleName, Set<Permissions> permissions) {
        this.id = id;
        this.roleName = roleName;
        this.permissions = permissions;
    }
}














