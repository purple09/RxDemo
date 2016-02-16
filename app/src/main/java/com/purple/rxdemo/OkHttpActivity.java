package com.purple.rxdemo;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.purple.rxdemo.okhttp.MyHttp;

import java.text.SimpleDateFormat;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OkHttpActivity extends AppCompatActivity {

    public static final String baseUrl = "http://10.47.17.135:3000";

    @Bind(R.id.et_content)
    EditText et;

    @Bind(R.id.bt)
    Button bt;

    @Bind(R.id.bt2)
    Button bt2;

    @Bind(R.id.tv_result)
    TextView tv;

    private Subscription subscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);

        Api api = initApi();

        bt.setOnClickListener(v -> {
            String content = et.getText().toString();
            if (content != null && !"".equals(content.trim())) {
                v.setEnabled(false);
                MyEntity entity = new MyEntity(content);
                String json = entity.toString();
                subscription = MyHttp.post(baseUrl + "/testPost", json)
                        .compose(observable -> observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread()))
                        .map(myHttpResult -> {
                            int code = myHttpResult.code;
                            String result = myHttpResult.json;
                            //这里是故意用map的形式。
                            Map<String, Object> map = new Gson().fromJson(result, new TypeToken<Map<String, Object>>() {
                            }.getType());

                            StringBuffer sb = new StringBuffer();
                            Observable.from(map.entrySet()).subscribe(entry ->
                                            sb.append(entry.getKey() + " ：" + entry.getValue() + "\n")
                            );

                            return sb.toString();
                        })
                        .subscribe(
                                str -> tv.setText(str),
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


        bt2.setOnClickListener(v -> {
            String content = et.getText().toString();
            if (content != null && !"".equals(content.trim())) {
                v.setEnabled(false);
                MyEntity entity = new MyEntity(content);
                subscription = api.post(entity)
                        .compose(observable -> observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread()))
                        .map(result -> {

                            Map<String, Object> map = new ArrayMap<>();
                            map.put("code", result.code);
                            map.put("msg", result.msg);
                            map.put("time", new SimpleDateFormat().format(result.time));
                            map.put("postinfo", result.postinfo);

                            return map;
                        })
                        .subscribe(

                                map -> {
                                    Log.e("okhttpactivity", new Gson().toJson(map));
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

    private Api initApi() {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Api.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && subscription.isUnsubscribed()) subscription.unsubscribe();
    }
}