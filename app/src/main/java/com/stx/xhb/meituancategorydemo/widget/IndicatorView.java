package com.stx.xhb.meituancategorydemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.stx.xhb.meituancategorydemo.R;
import com.stx.xhb.meituancategorydemo.utils.ScreenUtil;

/**
 * Author: Mr.xiao on 2017/5/23
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 指示器
 */
public class IndicatorView extends View {

    private int indicatorColor;
    private int indicatorColorSelected;
    private int indicatorWidth = 0;
    private int gravity = 0;
    private Paint mPaint;
    private int indicatorCount = 0;
    private int currentIndicator = 0;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x12) {
                invalidate();
            }
        }
    };

    public IndicatorView(Context context) {
        this(context, null, 0);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.IndicatorView);
            indicatorColor = typedArray.getColor(R.styleable.IndicatorView_indicatorColor, Color.rgb(0, 0, 0));
            indicatorColorSelected = typedArray.getColor(R.styleable.IndicatorView_indicatorColorSelected, Color.rgb(0, 0, 0));
            indicatorWidth = ScreenUtil.dip2px(typedArray.getInt(R.styleable.IndicatorView_indicatorWidth, 0));
            gravity = typedArray.getInt(R.styleable.IndicatorView_gravity, 0);
            typedArray.recycle();
        }
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        indicatorColor = Color.rgb(0, 0, 0);
        indicatorColorSelected = Color.rgb(0, 0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int totalWidth = indicatorWidth * (2 * indicatorCount - 1);

        if (indicatorCount > 0) {
            for (int i = 0; i < indicatorCount; i++) {
                if (i == currentIndicator) {
                    mPaint.setColor(indicatorColorSelected);
                } else {
                    mPaint.setColor(indicatorColor);
                }
                int left = (viewWidth - totalWidth) / 2 + (i * 2 * indicatorWidth);
                switch (gravity) {
                    case 0:
                        left = (viewWidth - totalWidth) / 2 + (i * 2 * indicatorWidth);
                        break;
                    case 1:
                        left = i * 2 * indicatorWidth;
                        break;
                    case 2:
                        left = viewWidth - totalWidth + (i * 2 * indicatorWidth);
                        break;
                    default:
                        break;
                }
                int top = (viewHeight - indicatorWidth) / 2;
                int right = left + indicatorWidth;
                int bottom = top + indicatorWidth;
                RectF rectF = new RectF(left, top, right, bottom);
                canvas.drawOval(rectF, mPaint);
            }
        }
    }

    public void setIndicatorCount(int indicatorCount) {
        this.indicatorCount = indicatorCount;
    }

    public void setCurrentIndicator(int currentIndicator) {
        this.currentIndicator = currentIndicator;
        handler.sendEmptyMessage(0x12);
    }

}
