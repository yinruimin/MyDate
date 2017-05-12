package com.example.user.mydate.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.mydate.R;


public class CustomToast {
    private TextView mMessage;
    private CustomToast mInstance;
    private Toast mToast;
    private View mToastRoot;
    private int mScreenHeight;

    public CustomToast(Context context) {
        mToast = new Toast(context);
        init(context);
    }

    private void init(Context context) {
        //加载Toast布局
        mToastRoot = LayoutInflater.from(context).inflate(R.layout.comm_toast_layout, null);
        //初始化布局控件
        mMessage = (TextView) mToastRoot.findViewById(R.id.toast_message);
        mScreenHeight = getScreenHeight(context);
    }

    public int getScreenHeight(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
    public void showToastLong(String message) {
        //为控件设置属性
        mMessage.setText(message);
        mToast.setGravity(Gravity.BOTTOM, 0, mScreenHeight / 10);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setView(mToastRoot);
        mToast.show();
    }

    public void showToastShort(String message) {
        mMessage.setText(message);
        mToast.setGravity(Gravity.BOTTOM, 0, mScreenHeight / 10);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(mToastRoot);
        mToast.show();
    }

}
