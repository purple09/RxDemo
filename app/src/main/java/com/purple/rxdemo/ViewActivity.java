package com.purple.rxdemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class ViewActivity extends BaseActivity {


    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_password)
    EditText et_password;
    @Bind(R.id.bt_login)
    Button bt_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        Observable<Boolean> observableUsername = RxTextView.textChanges(et_username).map(charSequence -> {
            String str = charSequence.toString().trim();
            return str != null && !"".equals(str);
        });
        Observable<Boolean> observablepassword = RxTextView.textChanges(et_password).map(charSequence -> {
            String str = charSequence.toString().trim();
            return str != null && !"".equals(str);
        });
        RxView.clicks(bt_login).subscribe(aVoid ->
                        Toast.makeText(ViewActivity.this, "点击了Login", Toast.LENGTH_SHORT).show()
        );

        rx(
                Observable.combineLatest(observableUsername, observablepassword,
                        (isUsernameOk, isPasswordOk) ->
                                isUsernameOk && isPasswordOk
                ).subscribe(isOk -> bt_login.setEnabled(isOk))
        );

    }
}
