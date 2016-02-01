package com.purple.rxdemo;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.purple.rxdemo.okhttp.MyHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OkHttpActivity extends AppCompatActivity {

    @Bind(R.id.et_content)
    EditText et;

    @Bind(R.id.bt)
    Button bt;

    @Bind(R.id.tv_result)
    TextView tv;

    private Subscription subscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);

        bt.setOnClickListener(v -> {
            String content = et.getText().toString();
            if (content != null && !"".equals(content.trim())) {
                v.setEnabled(false);
                MyEntity entity = new MyEntity(content);
                String json = JSON.toJSONString(entity);
                subscription = MyHttp.post("http://apis.baidu.com/tutusoft/shajj/shajj", json)
                        .compose(observable -> observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread()))
                        .map(myHttpResult -> {
                            int code = myHttpResult.code;
                            JSONObject body = myHttpResult.body;

                            Map<String, Object> map = new ArrayMap<>();
                            Iterator<String> iterator = body.keys();
                            try {
                                while (iterator.hasNext()) {
                                    String key = iterator.next();
                                    map.put(key, body.get(key));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return map;
                        })
                        .subscribe(
                                map -> {
                                    StringBuffer sb = new StringBuffer();
                                    for (String key : map.keySet()) {
                                        sb.append(key + " ：" + map.get(key) + "\n");
                                    }
                                    tv.setText(sb.toString());
                                },
                                error -> {
                                    error.printStackTrace();
                                    tv.setText(error.toString());
                                    v.setEnabled(true);
                                },
                                () -> v.setEnabled(true)
                        );
            } else {
                Toast.makeText(OkHttpActivity.this, "请输出内容", Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && subscription.isUnsubscribed()) subscription.unsubscribe();
    }
}