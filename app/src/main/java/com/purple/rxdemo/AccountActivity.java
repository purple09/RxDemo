package com.purple.rxdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;

public class AccountActivity extends BaseActivity {

    @Bind(R.id.tv_get)
    TextView tvGet;
    @Bind(R.id.bt_get)
    Button btGet;
    @Bind(R.id.tv_set)
    TextView tvSet;
    @Bind(R.id.tv_sample)
    TextView tvSample;
    @Bind(R.id.bt_set)
    Button btSet;
    @Bind(R.id.tv_refresh)
    TextView tvRefresh;
    @Bind(R.id.bt_refresh)
    Button btRefresh;
    @Bind(R.id.tv_get_refresh)
    TextView tvGetRefresh;
    @Bind(R.id.bt_get_refresh)
    Button btGetRefresh;
    @Bind(R.id.bt_clear)
    Button btClear;

    private AccountManager accountManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        accountManager = AccountManager.ins();
        accountManager.set("init");
        String text = accountManager.get();
        rx(
                accountManager.rx()
                        .subscribe(next -> {
                            Log.e("account", next);
                            tvSet.setText(next);
                           /* try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }*/
                            tvRefresh.setText(next);
                        }, error -> {
                            Toast.makeText(this, "error happens!", Toast.LENGTH_SHORT).show();
                        })
        );
        rx(
                //其实一般都不需要做sample处理，请求这么频繁就需要去review代码了。
                accountManager.rx().sample(200, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(next -> {
                            tvSample.setText(next);
                        }, error -> {
                            Toast.makeText(this, "error happens!", Toast.LENGTH_SHORT).show();
                        })
        );
    }

    @OnClick({R.id.bt_get, R.id.bt_set, R.id.bt_refresh, R.id.bt_get_refresh, R.id.bt_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get:
                tvGet.setText(accountManager.get());
                break;
            case R.id.bt_set:
                accountManager.set("set account");
                break;
            case R.id.bt_refresh:
                accountManager.refresh();
                break;
            case R.id.bt_get_refresh:
                rx(
                        accountManager.getRefresh().subscribe(next -> {
                            tvGetRefresh.setText(next);
                        }, error -> {
                            Toast.makeText(this, "error happens!", Toast.LENGTH_SHORT).show();
                        })
                );
                break;
            case R.id.bt_clear:
                accountManager.clear();
                break;
        }
    }
}
