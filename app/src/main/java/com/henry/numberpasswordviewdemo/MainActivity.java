package com.henry.numberpasswordviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.henry.numberpasswordview.NumberPasswordView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NumberPasswordView numberPasswordView = (NumberPasswordView) findViewById(R.id.npv_password_view);
        numberPasswordView.setOnInputNumberCodeCallback(new NumberPasswordView.OnInputNumberCodeCallback() {
            @Override
            public void onResult(String code) {
                Toast.makeText(MainActivity.this, code, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
