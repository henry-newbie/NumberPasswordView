package com.henry.numberpasswordview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by henry on 2016/8/30.
 */
public class NumberView extends LinearLayout {

    public static final int DEFAULT_PASSWORD_COUNT = 4;

    private Context context;

    public NumberView(Context context) {
        this(context, null);
    }

    public NumberView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        for (int i = 0; i < DEFAULT_PASSWORD_COUNT; i++) {
            TextView textView = new TextView(context);
            textView.setTextSize(22);
            textView.setTextColor(Color.parseColor("#C0C0C0"));
            textView.setText("●");
            textView.setPadding(18, 18, 18, 18);
            setOrientation(HORIZONTAL);
            addView(textView);
        }
    }

    public void setEnteredCount(int count) {
        for (int i = 0; i < DEFAULT_PASSWORD_COUNT; i ++) {
            TextView textView = (TextView) getChildAt(i);
            if(i < count) {
                textView.setTextColor(Color.parseColor("#000000"));
            } else {
                textView.setTextColor(Color.parseColor("#C0C0C0"));
            }
        }
    }
}
