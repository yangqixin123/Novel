package com.sxau.novel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class CircleProgressbar extends View {

    /**
     * 圆圈的颜色
     */
    private int mCircleColor;

    /**
     * 圆圈的宽度
     */
    private int mCircleWith;

    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 当前进度
     */
    private int mProgress;

    /**
     * 是否正在运行
     */
    public boolean isRunning = true;


    public CircleProgressbar(Context context) {
        this(context, null);
    }

    public CircleProgressbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 必要的初始化，获得一些自定义的值
     *
     * @param context      上下文
     * @param attrs        attrs
     * @param defStyleAttr defStyleAttr
     */
    public CircleProgressbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CountdownCircleProgressBar, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.CountdownCircleProgressBar_circleColor:
                    mCircleColor = array.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CountdownCircleProgressBar_circleWith:
                    mCircleWith = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }
        array.recycle();

        mPaint = new Paint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取圆心坐标
        int centre = getWidth() / 2;
        //半径
        int radius = centre - mCircleWith / 2;
        //设置圆环宽度
        mPaint.setStrokeWidth(mCircleWith);
        //消除锯齿
        mPaint.setAntiAlias(true);
        //设置空心
        mPaint.setStyle(Paint.Style.STROKE);

        //用于定义的圆弧的形状和大小的界限
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);

        //canvas.drawCircle(centre, centre, radius, mPaint);//换出圆环
        //设置圆环的颜色
        mPaint.setColor(mCircleColor);
        //根据进度画圆弧: 顺时针画圆弧
        canvas.drawArc(oval, -90, mProgress, false, mPaint);
        //根据进度画圆弧 : 逆时针画圆弧
        //canvas.drawArc(oval, -90, -mProgress, false, mPaint);
    }


    /**
     * 播放倒计时动画
     */
    public void play() {
        //绘制线程
        new Thread() {
            @Override
            public void run() {
                while (isRunning) {
                    mProgress++;
                    postInvalidate();
                    try {
                        Thread.sleep(timeMillis / 360);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


    /**
     * 倒计时时间
     */
    private long timeMillis = 5000;

    /**
     * 设置倒计时时间
     */
    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
        invalidate();
    }
}


