package com.mysonandme.pojo.sys;


import lombok.Data;

@Data
public class Permissions {
    private String id;
    private String permissionsName;
    //省略set、get方法等.....


    public Permissions(String id, String permissionsName) {
        this.id = id;
        this.permissionsName = permissionsName;
    }
}





