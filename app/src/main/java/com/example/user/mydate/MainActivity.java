package com.example.user.mydate;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.user.mydate.view.CircleNumberProgress;
import com.example.user.mydate.view.CommonDialog;
import com.example.user.mydate.view.CustomToast;
import com.example.user.mydate.view.SelectDatePopupWindow;
import com.example.user.mydate.view.SelectDevicePopupWindow;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private TextView selectDate;
    private TextView selectDevice;
    private ArrayList<String> list;
    private TextView circle;
    private CircleNumberProgress circle_container;
    private TextView dialog;
    private TextView toast;
    private CustomToast customToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        selectDate = (TextView) findViewById(R.id.selectDate);
        selectDevice = (TextView) findViewById(R.id.selectThing);
        circle = (TextView) findViewById(R.id.circle);
        circle_container = (CircleNumberProgress) findViewById(R.id.circle_container);
        dialog = (TextView) findViewById(R.id.dialog);
        toast = (TextView) findViewById(R.id.toast);

    }

    public void select(View v) {
        if (v.getId() == R.id.selectDate) {
            select();
        } else if (v.getId() == R.id.selectThing) {
            selectDevice();
        } else if (v.getId() == R.id.circle) {
            changePercent();
        } else if (v.getId() == R.id.dialog) {
            showCommonDialog("提示信息", "确定激活任务");
        } else if (v.getId() == R.id.toast) {
            showToast();
        }

    }

    private void changePercent() {
        float percent = (float) (Math.random() * 100);
        circle_container.setCurrentPercent(percent);
    }


    private void showToast() {
        customToast = new CustomToast(this);
        customToast.showToastShort("请输入高压阀值");
    }

    /**
     * 显示更新对话框
     */
    private void showCommonDialog(String title, String message) {
        final CommonDialog dialog = new CommonDialog(this);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }



    private void selectDevice() {
        SelectDevicePopupWindow selectDevicePopupWindow = new SelectDevicePopupWindow(this);
        selectDevicePopupWindow.showAtLocation(selectDevice, Gravity.BOTTOM, 0, 0);
        list = new ArrayList<>();
        list.add("水压表");
        list.add("消防栓");
        list.add("无线烟感");
        selectDevicePopupWindow.setData(list);
        selectDevicePopupWindow.setOnDeviceChangeListener(new SelectDevicePopupWindow.onDeviceChangeListener() {
            @Override
            public void onClick(String string) {
                selectDevice.setText(string);
            }
        });
    }


    private void select() {
        SelectDatePopupWindow mChangeBirthDialog = new SelectDatePopupWindow(this);
        mChangeBirthDialog.showAtLocation(selectDate, Gravity.BOTTOM, 0, 0);
        mChangeBirthDialog.setBirthdayListener(new SelectDatePopupWindow.OnBirthListener() {

            @Override
            public void onClick(String year, String month, String day) {
                selectDate.setText(year + month + day);
            }
        });
    }

}
