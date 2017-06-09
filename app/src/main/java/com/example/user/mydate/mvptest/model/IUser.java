package com.example.user.mydate.mvptest.model;

/**
 * 创建者: YIN
 * 创建时间: 2017/6/9 17:31
 * 电子邮箱: yin_ruimin@foxmail.com
 * 描述:
 */

public interface IUser {
    String getName();

    String getPassword();

    int checkUserValidity(String name, String password);
}
