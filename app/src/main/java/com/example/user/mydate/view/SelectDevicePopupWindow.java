package com.example.user.mydate.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.user.mydate.R;
import com.example.user.mydate.view.wheelview.OnWheelChangedListener;
import com.example.user.mydate.view.wheelview.OnWheelScrollListener;
import com.example.user.mydate.view.wheelview.WheelView;
import com.example.user.mydate.view.wheelview.adapter.MyAbstractWheelTextAdapter;

import java.util.ArrayList;

/**
 * 创建者: YIN
 * 创建时间: 2017/5/11 15:24
 * 电子邮箱: yin_ruimin@foxmail.com
 * 描述: 设备滚轮选择PopupWindow
 */

public class SelectDevicePopupWindow extends PopupWindow implements View.OnClickListener {

    private MyCalendarTextAdapter stringArrayWheelAdapter;
    private Context context;
    private final View view;
    private final WheelView mWheelView;
    private final TextView mSure;
    private final TextView mCancel;
    private final LinearLayout mContainer;
    private final TranslateAnimation mShowAction;

    private ArrayList<String> device_list = new ArrayList<>();

    private int maxTextSize ;
    private int minTextSize ;

    private onDeviceChangeListener listener;

    private String selectDevice;

    public SelectDevicePopupWindow(Context context) {
        super(context);
        this.context = context;
        view = View.inflate(context, R.layout.popupwindow_select_device, null);
        mWheelView = (WheelView) view.findViewById(R.id.wv_device);
        mSure = ((TextView) view.findViewById(R.id.btn_sure));
        mCancel = ((TextView) view.findViewById(R.id.btn_cancel));
        mContainer = ((LinearLayout) view.findViewById(R.id.ll_container));

        maxTextSize = context.getResources().getDimensionPixelSize(R.dimen.x27);
        minTextSize = context.getResources().getDimensionPixelSize(R.dimen.x20);
        //设置的View
        this.setContentView(view);
        //设置弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置弹出窗体可点击
        this.setFocusable(true);
        //设置弹出窗体外部可点击
        this.setOutsideTouchable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
                0.0f);
        mShowAction.setDuration(200);
        mContainer.setAnimation(mShowAction);

        mSure.setOnClickListener(this);
        mCancel.setOnClickListener(this);

        stringArrayWheelAdapter = new MyCalendarTextAdapter(context, device_list, 0, maxTextSize, minTextSize);
        mWheelView.setVisibleItems(5);
        mWheelView.setViewAdapter(stringArrayWheelAdapter);
        mWheelView.setCurrentItem(0);

        mWheelView.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) stringArrayWheelAdapter.getItemText(wheel.getCurrentItem());
                selectDevice = currentText;
                selectDevice = device_list.get(mWheelView.getCurrentItem());

                setTextviewSize(currentText, stringArrayWheelAdapter);
            }
        });
        mWheelView.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) stringArrayWheelAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, stringArrayWheelAdapter);
            }
        });
    }


    public void setData(ArrayList<String> strings) {
        device_list.addAll(strings);
        selectDevice = device_list.get(0);
    }

    public interface onDeviceChangeListener {
        void onClick(String string);
    }

    public void setOnDeviceChangeListener(onDeviceChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sure) {
            if (listener != null) {
                listener.onClick(selectDevice);
            }
        }
        dismiss();
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, MyAbstractWheelTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(TypedValue.COMPLEX_UNIT_PX, maxTextSize);
            } else {
                textvew.setTextSize(TypedValue.COMPLEX_UNIT_PX, minTextSize);
            }
        }
    }

    private class MyCalendarTextAdapter extends MyAbstractWheelTextAdapter {
        ArrayList<String> list;

        protected MyCalendarTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.popupwindow_item, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }
    }
}
