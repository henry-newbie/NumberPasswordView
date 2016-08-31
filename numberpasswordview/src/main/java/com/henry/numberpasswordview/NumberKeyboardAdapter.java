package com.henry.numberpasswordview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by henry on 2016/8/30.
 */
public class NumberKeyboardAdapter extends BaseAdapter {

    public static final String NUMBERS = "123456789";
    public static final String KEY_CLEAR = "清空";
    public static final String KEY_ZERO = "0";
    public static final String KEY_DELETE = "删除";

    private String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "清空", "0", "删除"};

    Context mContext;

    public NumberKeyboardAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public String getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView tvNumber;
        if (convertView == null) {
            tvNumber = new TextView(mContext);
            tvNumber.setTextSize(28);
            tvNumber.setGravity(Gravity.CENTER);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    120);
            tvNumber.setLayoutParams(layoutParams);
        } else {
            tvNumber = (TextView) convertView;
        }

        tvNumber.setText(getItem(position));
        return tvNumber;
    }

    private void getBackground() {
        GradientDrawable gradientDrawableMiddle = new GradientDrawable();
        gradientDrawableMiddle.setShape(GradientDrawable.OVAL);
        gradientDrawableMiddle.setColor(Color.parseColor("#ffa025"));
        gradientDrawableMiddle.setSize(50, 50);
    }
}
