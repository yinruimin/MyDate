package com.example.user.mydate.mvptest.presenter;

/**
 * 创建者: YIN
 * 创建时间: 2017/6/9 17:31
 * 电子邮箱: yin_ruimin@foxmail.com
 * 描述:
 */

public interface ILoginPresenter {
    void clear();

    void login(String name, String password);

    void setProgressVisibility(int visibility);
}
