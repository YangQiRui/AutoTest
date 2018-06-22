package com.course.modle;

import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private int age;
    private int sex;
    private String permission;
    private String idDelete;
}
