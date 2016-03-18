package com.example.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

/**
 * Created by Nick on 2016/3/3.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "Nick---LoginActivity";
    private EditText account;
    private EditText password;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "LoginActivity.onCreate.this==" + this.toString());
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        account = (EditText) findViewById(R.id.account_edit);
        password = (EditText) findViewById(R.id.password_edit);
        Button login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(this);
        CheckBox rememberCb = (CheckBox) findViewById(R.id.remember_cb);
        rememberCb.setOnCheckedChangeListener(this);
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getBoolean("is_remembered",false)){
            account.setText(sharedPreferences.getString("account",""));
            password.setText(sharedPreferences.getString("password",""));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button: {
                String account_str = account.getText().toString();
                String password_str = password.getText().toString();
                if (account_str.equals("admin") && password_str.equals("12345")) {
                    editor.putString("account",account_str);
                    editor.putString("password",password_str);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    makeText(LoginActivity.this, "Account or password is available!", LENGTH_SHORT).show();
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.remember_cb:
                if (isChecked) {
                    editor.putBoolean("is_remembered", true);
                } else {
                    editor.putBoolean("is_remembered", false);
                }
                editor.commit();
                break;
            default:
                break;
        }
    }
}
