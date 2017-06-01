package com.example.user.mydate.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.mydate.R;

/**
 * 创建者: YIN
 * 创建时间: 2017/5/11 17:21
 * 电子邮箱: yin_ruimin@foxmail.com
 * 描述: 百分比圆环
 */
public class CircleNumberProgress extends View {

    private Paint mPaintCircleGray, mPaintPink, mPaintNumber, mPaintPercent, mPaintText;
    private RectF rectf;
    private float baseDimension;
    private int mCircleGrayColor = Color.parseColor("#d8d8d8"); // 浅灰色
    private int mTextGrayColor = Color.parseColor("#999999");//深灰色
    private int mCirclePinkColor = Color.parseColor("#f9709a");//粉红色
    private int mTextBlackColor = Color.parseColor("#666666");//黑色
    private String percentLogo = "%";
    private String completeText = "完成率";
    private float radius;
    private float circlePadding;
    private Resources resource;
    private float mCurPercent = 0f;

    public CircleNumberProgress(Context context) {
        this(context, null);
    }

    public CircleNumberProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleNumberProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        resource = getResources();
        baseDimension = resource.getDimension(R.dimen.x10);

        //圆圈颜色画笔
        mPaintCircleGray = new Paint();
        mPaintCircleGray.setAntiAlias(true);
        mPaintCircleGray.setColor(mCircleGrayColor);
        mPaintCircleGray.setStrokeWidth(baseDimension * 0.5f);
        mPaintCircleGray.setStyle(Paint.Style.STROKE);

        // 大数字 90
        mPaintNumber = new Paint();
        mPaintNumber.setAntiAlias(true);
        mPaintNumber.setColor(mTextBlackColor);
        mPaintNumber.setTextSize(resource.getDimension(R.dimen.y27));
        mPaintNumber.setTextAlign(Paint.Align.CENTER);
        mPaintNumber.setStyle(Paint.Style.FILL);

        // "%"
        mPaintPercent = new Paint();
        mPaintPercent.setAntiAlias(true);
        mPaintPercent.setColor(mTextBlackColor);
        mPaintPercent.setTextSize(resource.getDimension(R.dimen.y27));
        mPaintPercent.setStyle(Paint.Style.FILL);

        //完成率
        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(mTextGrayColor);
        mPaintText.setTextSize(resource.getDimension(R.dimen.x19));
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setStyle(Paint.Style.FILL_AND_STROKE);

        //圆圈带颜色
        mPaintPink = new Paint();
        mPaintPink.setAntiAlias(true);
        mPaintPink.setColor(mCirclePinkColor);
        mPaintPink.setStrokeWidth(baseDimension * 0.5f);
        mPaintPink.setTextAlign(Paint.Align.CENTER);
        mPaintPink.setStyle(Paint.Style.STROKE);

        //外圈圆的位置、直径70dp、距离顶部、左边各5dp
        rectf = new RectF();
        radius = resource.getDimension(R.dimen.x116);
        circlePadding = resource.getDimension(R.dimen.x5);
        rectf.set(circlePadding, circlePadding, radius, radius);

    }

    protected void onDraw(Canvas c) {
        super.onDraw(c);

        //画最外圈灰色的圆
        c.drawArc(rectf, -90, 360, false, mPaintCircleGray);
        //画最外圈，带颜色的进度条圆
        c.drawArc(rectf, -90, 360 * mCurPercent / 100, false, mPaintPink);
        //画百分比数字
        c.drawText(mCurPercent + "%", baseDimension * 6.0f, baseDimension * 6.0f, mPaintNumber);
        //画下面的文字，完成率
        c.drawText(completeText, baseDimension * 6.0f, baseDimension * 8.5f, mPaintText);
    }

    public void setCurrentPercent(float curPercent) {
        ValueAnimator anim = ValueAnimator.ofFloat(mCurPercent, curPercent);
        anim.setDuration((long) (Math.abs(mCurPercent - curPercent) * 20));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mCurPercent = (float) (Math.round(value * 10)) / 10;//四舍五入保留到小数点后两位
                invalidate();
            }
        });
        anim.start();
    }


}

