package com.purple.rxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String s = "\n";
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        ArrayList<Pair<String, Class>> datas = new ArrayList<>();
        initDatas(datas);
        MainAdapter adapter = new MainAdapter(datas);
        rx(
                adapter.mSubject.subscribe(position -> {
                    startActivity(new Intent(MainActivity.this, datas.get(position).second));
                    //finish();
                })
        );
        recyclerView.setAdapter(adapter);
    }


    private void initDatas(List<Pair<String, Class>> datas) {
        datas.add(new Pair("just test", TestActivity.class));
        datas.add(new Pair("timer", TimerActivity.class));
        datas.add(new Pair("okhttp", OkHttpActivity.class));
        datas.add(new Pair("view-RxBinding", ViewActivity.class));
        datas.add(new Pair("Error", ErrorActivity.class));
        // datas.add(new Pair("Main2Activity", Main2Activity.class));
    }
}
