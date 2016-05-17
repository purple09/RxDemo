package com.purple.rxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JumpActivity extends AppCompatActivity {

    @Bind(R.id.bt_set_data)
    Button btSetData;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
        ButterKnife.bind(this);
        num = 1;
    }

    @OnClick(R.id.bt_set_data)
    public void onClick() {
        AccountManager.ins().set("jump" + (num++));
    }
}
