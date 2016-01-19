package com.purple.rxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    @Bind(R.id.tv_test)
    TextView tv;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(MyBroadcastReceiver.ACTION);
            intent.putExtra("time", new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()));
            sendBroadcast(intent);
        });

        MyBroadcastReceiver.mObservable
                .map(intent -> intent.getStringExtra("time"))
                .filter(time -> time != null && !"".equals(time))
                .subscribe(time -> tv.setText(time));


    }

}
