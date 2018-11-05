package com.wrq.nathaniel.wangrongqi20181105;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import java.lang.annotation.Repeatable;

public class Choujaing extends View implements View.OnClickListener {
    private boolean isStart = false;
    private Paint paint1;
    private Paint paint2;
    private RectF rectF;
    private int[] colors = new int[]{
      Color.RED,Color.BLACK,Color.YELLOW,Color.GREEN,Color.GRAY,Color.BLUE
    };
    private String[] name = new String[]{
      "一","二","三","四","五","六",
    };
    private RotateAnimation rotateAnimation;

    public Choujaing(Context context) {
        this(context,null);
    }

    public Choujaing(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public Choujaing(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initAnim();
    }

    private void initAnim() {
        rotateAnimation = new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setFillAfter(true);
//        rotateAnimation.setInterpolator(0,360);
    }

    private void initPaint() {
        rectF = new RectF(200,200,600,600);
        paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(5);
        paint1.setAntiAlias(true);
        paint2 = new Paint();
        paint1.setColor(Color.BLUE);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(5);
        paint1.setAntiAlias(true);
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(800,800);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
     canvas.drawCircle(400,400,200,paint1);
     float start  =60;
        for (int i = 0; i < 6; i++) {
            paint1.setColor(colors[i]);
            canvas.drawArc(rectF,start*i,60,true,paint1);
        }
        for (int i = 0; i < 6; i++) {
            paint2.setColor(Color.WHITE);
            paint2.setTextSize(20);
            Path path = new Path();
            path.addArc(rectF,(float)(60*i)+60,60);
            canvas.drawTextOnPath(name[i],path,90,45,paint2);
        }

        canvas.drawCircle(400,400,80,paint2);
        paint2.setColor(Color.BLACK);
        canvas.drawText("start",380,400,paint2);
    }

    @Override
    public void onClick(View v) {
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isStart=true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isStart=false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        if (!isStart){
            rotateAnimation.setDuration(1000);
            rotateAnimation.setInterpolator(new AccelerateInterpolator());
            startAnimation(rotateAnimation);
        }
        else {
            isStart=true;
            clearAnimation();

        }
    }
}
