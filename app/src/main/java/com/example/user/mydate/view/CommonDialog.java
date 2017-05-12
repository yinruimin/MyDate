package com.example.user.mydate.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.user.mydate.R;


/**
 * Created by ice on 2017/3/27.
 */

public class CommonDialog extends Dialog {
    private TextView mMessage;
    private TextView mCancel;
    private TextView mConfirm;
    private TextView mTitle;
    private View mDivide;

    public CommonDialog(Context context) {
        super(context, R.style.custom_dialog);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.common_dialog, null);
        mMessage = ((TextView) view.findViewById(R.id.dialog_message));
        mCancel = ((TextView) view.findViewById(R.id.dialog_cancel));
        mConfirm = ((TextView) view.findViewById(R.id.dialog_confirm));
        mTitle = (TextView) view.findViewById(R.id.dialog_title);
        mDivide = view.findViewById(R.id.dialog_divide);
        setContentView(view);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    public void setMessage(String message) {
        mMessage.setText(message);
    }

    public void setMessageColor(int color){
        mMessage.setTextColor(color);
    }

    public void setTitle(String message) {
        mTitle.setText(message);
    }

    public void setTitleColor(int color){
        mTitle.setTextColor(color);
    }

    public void setCancelListener(View.OnClickListener onClickListener) {
        mCancel.setOnClickListener(onClickListener);
    }

    public void setConfirmListener(View.OnClickListener onClickListener) {
        mConfirm.setOnClickListener(onClickListener);
    }

    public void setConfirmText(String text) {
        mConfirm.setText(text);
    }

    public void setConfirmColor(int color){
        mConfirm.setTextColor(color);
    }

    public void setCancelText(String text) {
        mCancel.setText(text);
    }

    public void setCancelColor(int color){
        mCancel.setTextColor(color);
    }


}
