package com.henry.numberpasswordview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Stack;

/**
 * Created by henry on 2016/8/30.
 */
public class NumberPasswordView extends LinearLayout {

    private final static int NUMBER_BUTTON_DELETE = 11; // 删除按键
    private final static int NUMBER_BUTTON_CLEAR = 9;   // 清空按键
    private final static int NUMBER_COUNT = 4;          // 4位密码

    private Context context;

    private Stack<String> passwordStack;

    private GridView gvKeyboard;

    private NumberView nvPassword;

    private OnInputNumberCodeCallback onInputNumberCodeCallback;

    public NumberPasswordView(Context context) {
        this(context, null);
    }

    public NumberPasswordView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberPasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        passwordStack = new Stack<>();

        View view = inflate(context, R.layout.view_number_password, this);
        gvKeyboard = (GridView) view.findViewById(R.id.gv_keyboard);
        nvPassword = (NumberView) view.findViewById(R.id.nv_password);

        final NumberKeyboardAdapter adapter = new NumberKeyboardAdapter(context);
        gvKeyboard.setAdapter(adapter);

        gvKeyboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == NUMBER_BUTTON_CLEAR) {
                    // 清空
                    if(!passwordStack.empty()) {
                        passwordStack.clear();
                        // 刷新界面
                        nvPassword.setEnteredCount(0);
                    }
                    return;
                } else if (position == NUMBER_BUTTON_DELETE) {
                    if (passwordStack.empty() || passwordStack.size() > NUMBER_COUNT) {
                        return;
                    }
                    // 删除
                    passwordStack.pop();
                    // 刷新界面
                    nvPassword.setEnteredCount(passwordStack.size());
                    return;
                } else {
                    if(passwordStack.size() < 4) {
                        passwordStack.push(adapter.getItem(position));
                        // 刷新界面
                        nvPassword.setEnteredCount(passwordStack.size());
                    }
                }

                if (passwordStack.size() == NUMBER_COUNT) {
                    Toast.makeText(context, getPassword(), Toast.LENGTH_SHORT).show();
                    // 输入满4位密码，回调
                    if(onInputNumberCodeCallback != null) {
                        onInputNumberCodeCallback.onResult(getPassword());
                    }
                }
            }
        });
    }

    private String getPassword() {
        StringBuilder password = new StringBuilder();
        for (String number: passwordStack) {
            password.append(number);
        }
        return password.toString();
    }

    /**
     * 设置回调监听
     * @param onInputNumberCodeCallback
     */
    public void setOnInputNumberCodeCallback(OnInputNumberCodeCallback onInputNumberCodeCallback) {
        this.onInputNumberCodeCallback = onInputNumberCodeCallback;
    }

    public interface OnInputNumberCodeCallback {
        void onResult(String code);
    }
}
