package com.example.androidfirstclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView mImgHead;
    private EditText mEtlogin;
    private EditText mEtPassword;
    private TextInputLayout mTilLogin;
    private TextInputLayout mTilPassword;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private CheckBox mCbRemember;
    private SharedPreferences prefAccount;
    private SharedPreferences prefPassword;
    private SharedPreferences.Editor editorAccount;
    private SharedPreferences.Editor editorPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mBtnLogin.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
    }

    private void initView() {
        mImgHead=findViewById(R.id.img_main_head);
        mEtlogin=findViewById(R.id.et_main_login);
        mEtPassword=findViewById(R.id.et_main_password);
        mBtnLogin=findViewById(R.id.btn_main_login);
        mBtnRegister=findViewById(R.id.btn_main_register);
        mCbRemember=findViewById(R.id.cb_remmber_pass);
        mTilLogin=findViewById(R.id.til_main_register);
        mTilPassword=findViewById(R.id.til_main_password);
//        pref= PreferenceManager.getDefaultSharedPreferences(this);
        prefAccount = getSharedPreferences("dataAccount",MODE_PRIVATE);
        prefPassword = getSharedPreferences("dataPassword",MODE_PRIVATE);
        editorAccount = prefAccount.edit();
        editorPassword = prefPassword.edit();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_main_login:

                hit();
                break;
            case R.id.btn_main_register:
                Toast.makeText(MainActivity.this, "跳转注册页面……", Toast.LENGTH_SHORT).show();
                register();
                break;
        }
    }
    private void hit(){
        String account = mEtlogin.getText().toString();
        String password = mEtPassword.getText().toString();

        mTilLogin.setErrorEnabled(false);
        mTilPassword.setErrorEnabled(false);

        //验证用户名和密码
        if(validateAccount(account)&&validatePassword(password)){
            login();
        }
    }

    private void register() {
        RegisterDialogFragment registerDialogFragment = RegisterDialogFragment.newInstance("");
        registerDialogFragment.show(getSupportFragmentManager(), "edit");
//        RegisterDialogFragment registerDialogFragment = new RegisterDialogFragment();
//        registerDialogFragment.show(getSupportFragmentManager(),"registerDialogFragment");
    }

    private void login() {
        boolean isRemember = prefPassword.getBoolean("remember_password",false);
        if(isRemember){
//            prefAccount = getSharedPreferences("dataAccount",MODE_PRIVATE);
//             SharedPreferences prefLogin = getSharedPreferences("dataAccount",MODE_PRIVATE);

//            Toast.makeText(this, "账户："+account, Toast.LENGTH_SHORT).show();

//            Toast.makeText(this, "密码："+password, Toast.LENGTH_SHORT).show();
//            mCbRemember.setChecked(true);
//            Toast.makeText(this, "333333", Toast.LENGTH_SHORT).show();
            editorAccount.putString("account",prefAccount.getString("account",""));
            editorPassword.putString("password",prefPassword.getString("password",""));
        }
        if (ifLogin()) {
            rememberWordPut();
            loginSuccess();
//            Toast.makeText(this, "111111", Toast.LENGTH_SHORT).show();
        } else {
            loginFailure();
//            Toast.makeText(this, "222222", Toast.LENGTH_SHORT).show();
        }
    }

    private void rememberWordPut(){
        String account = prefAccount.getString("account","");
        String password = prefPassword.getString("password","");
        if(mCbRemember.isChecked()){
            editorPassword.putBoolean("remember_password",true);
//            editorAccount.putString("account",prefAccount.getString("account",""));
//            editorPassword.putString("password",prefPassword.getString("password",""));
            mEtlogin.setText(account);
            mEtPassword.setText(password);
        }else{
            mEtlogin.setText("");
            mEtPassword.setText("");
        }
//        else{
//            editorAccount.clear();
//            editorPassword.clear();
//        }
        editorAccount.apply();
        editorPassword.apply();

    }

    private boolean ifLogin(){
        String username = Objects.requireNonNull(mEtlogin.getText()).toString();
        String password = Objects.requireNonNull(mEtPassword.getText()).toString();

        return (password.equals(prefPassword.getString("password",""))&&username.equals(prefAccount.getString("account", "")))&&(password.trim().length()!=0);
    }

    private void loginFailure() {
        Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    private void loginSuccess() {
        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
        enterIntent();
    }

    private void enterIntent(){
//        Intent intent = new Intent(MainActivity.this,newIntent.class);
        Intent intent = new Intent(MainActivity.this,DrawerActivity.class);
        startActivity(intent);

    }

    private void setError(){
        Objects.requireNonNull(mTilLogin.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if( mTilLogin.getEditText().getText().toString().trim().length()>10){
                    mTilLogin.setError("用户名长度超出限制");
                }else{
                    mTilLogin.setError(null);
                }
            }
        });
    }
    private boolean validateAccount(String account){
        if(account.trim().length()==0){
            showError(mTilLogin,"用户名不能为空");
            return false;
        }
        if (account.length() < 6 || account.length() > 18) {
            showError(mTilPassword,"账户长度为6-18位");
            return false;
        }
        return true;
    }

    private boolean validatePassword(String password) {
        if(password.trim().length()==0){
            showError(mTilPassword,"密码不能为空");
            return false;
        }

        if (password.length() < 6 || password.length() > 18) {
            showError(mTilPassword,"密码长度为6-18位");
            return false;
        }

        return true;
    }
    private void showError(TextInputLayout textInputLayout, String error){
        textInputLayout.setError(error);
        Objects.requireNonNull(textInputLayout.getEditText()).setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }
}