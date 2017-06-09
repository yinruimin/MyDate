package com.example.user.mydate.mvptest.view;

/**
 * 创建者: YIN
 * 创建时间: 2017/6/9 17:31
 * 电子邮箱: yin_ruimin@foxmail.com
 * 描述:
 */

public interface ILoginView {
    void onClearText();

    void onLoginResult(Boolean result, int code);

    void onSetProgressVisibility(int visibility);
}
