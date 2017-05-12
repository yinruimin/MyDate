package com.example.user.mydate.view;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

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
    private int blackColor = Color.parseColor("#d8d8d8"); // 浅灰色
    private int textBlackColor = Color.parseColor("#999999");//深灰色
    private int mainColor = Color.parseColor("#f9709a");//粉红色
    private int black = Color.parseColor("#666666");//黑色
    private int score;
    private String unit, title;
    private float arc_y = 0f;
    private int score_text;
    private Activity activity;
    private float radius;
    private float circlePadding;
    private Resources res;

    /**
     * @param score 数字
     * @param unit  百分号
     * @param title 数字下面的文字
     */
    public CircleNumberProgress(Activity activity, int score, String unit, String title) {
        super(activity);
        init(activity, score, unit, title);
    }

    public void init(Activity activity, int score, String unit, String title) {
        this.activity = activity;
        this.score = score;
        this.unit = unit;
        this.title = title;


        res = getResources();
        baseDimension = res.getDimension(R.dimen.x10);

        //圆圈颜色画笔
        mPaintCircleGray = new Paint();
        mPaintCircleGray.setAntiAlias(true);
        mPaintCircleGray.setColor(blackColor);
        mPaintCircleGray.setStrokeWidth(baseDimension * 0.5f);
        mPaintCircleGray.setStyle(Paint.Style.STROKE);

        // 大数字 90
        mPaintNumber = new Paint();
        mPaintNumber.setAntiAlias(true);
        mPaintNumber.setColor(black);
        mPaintNumber.setTextSize(res.getDimension(R.dimen.y27));
        mPaintNumber.setTextAlign(Paint.Align.CENTER);
        mPaintNumber.setStyle(Paint.Style.FILL);

        // "%"
        mPaintPercent = new Paint();
        mPaintPercent.setAntiAlias(true);
        mPaintPercent.setColor(black);
        mPaintPercent.setTextSize(res.getDimension(R.dimen.y27));
        mPaintPercent.setStyle(Paint.Style.FILL);

        //完成率
        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(textBlackColor);
        mPaintText.setTextSize(res.getDimension(R.dimen.x19));
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setStyle(Paint.Style.FILL_AND_STROKE);

        //圆圈带颜色
        mPaintPink = new Paint();
        mPaintPink.setAntiAlias(true);
        mPaintPink.setColor(mainColor);
        mPaintPink.setStrokeWidth(baseDimension * 0.5f);
        mPaintPink.setTextAlign(Paint.Align.CENTER);
        mPaintPink.setStyle(Paint.Style.STROKE);

        //外圈圆的位置、直径70dp、距离顶部、左边各5dp
        rectf = new RectF();
        radius = res.getDimension(R.dimen.x116);
        circlePadding = res.getDimension(R.dimen.x5);
        rectf.set(circlePadding, circlePadding, radius, radius);
        setLayoutParams(new ViewGroup.LayoutParams(((int) (radius + circlePadding)), (int) (radius + circlePadding)));

        this.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    public boolean onPreDraw() {
                        new thread();
                        getViewTreeObserver().removeOnPreDrawListener(this);
                        return false;
                    }
                });
    }

    protected void onDraw(Canvas c) {
        super.onDraw(c);

        //画最外圈灰色的圆
        c.drawArc(rectf, -90, 360, false, mPaintCircleGray);
        //画最外圈，带颜色的进度条圆
        c.drawArc(rectf, -90, arc_y, false, mPaintPink);
        //画百分比数字
        c.drawText("" + score_text, radius / 2 - res.getDimension(R.dimen.y27) / 3, baseDimension * 6f, mPaintNumber);
        //画百分号
        c.drawText(unit, baseDimension * 6.4f, baseDimension * 6f, mPaintPercent);
        //画下面的文字，完成率
        c.drawText(title, baseDimension * 6.0f, baseDimension * 8.5f, mPaintText);
    }

    class thread implements Runnable {
        private Thread thread;
        private int statek;
        int count;

        public thread() {
            thread = new Thread(this);
            thread.start();
        }

        public void run() {
            while (true) {
                switch (statek) {
                    case 0:
                        try {
                            Thread.sleep(200);
                            statek = 1;
                        } catch (InterruptedException e) {
                        }
                        break;
                    case 1:
                        try {
                            Thread.sleep(15);
                            arc_y += 3.6f;
                            score_text++;
                            count++;
                            postInvalidate();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                if (count >= score)
                    break;
            }
        }
    }

}

