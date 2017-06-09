package com.example.user.mydate.mvptest.model;

/**
 * 创建者: YIN
 * 创建时间: 2017/6/9 17:31
 * 电子邮箱: yin_ruimin@foxmail.com
 * 描述:
 */

public class UserModel implements IUser {
    private String password;
    private String name;

    public UserModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int checkUserValidity(String name, String password) {
        if (name == null || password == null || !name.equals(getName()) || !password.equals(getPassword())) {
            return -1;
        }
        return 0;
    }
}
