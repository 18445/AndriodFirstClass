package com.example.androidfirstclass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class RegisterDialogFragment extends DialogFragment implements View.OnClickListener{
//public class RegisterDialogFragment extends DialogFragment {
    private Button mBtnRegister;
    private Button mBtnCancel;
    private EditText mEtRegister;
    private EditText mEtPassword;
    private TextInputLayout mTilRegister;
    private TextInputLayout mTilPassword;

    public void initView(View view){
        mBtnRegister = view.findViewById(R.id.btn_register);
        mBtnCancel = view.findViewById(R.id.btn_cancel);
        mEtRegister = view.findViewById(R.id.ed_register);
        mEtPassword = view.findViewById(R.id.ed_password);
        mTilRegister = view.findViewById(R.id.til_register);
        mTilPassword = view.findViewById(R.id.til_password);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_dialog_layout,container, false);
        initView(view);
        mBtnCancel.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
        return view;
    }

     @Override
    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        String tittle = getArguments().getString("tittle");
        ((EditText) view.findViewById(R.id.ed_register)).setText(tittle);
        EditText editText = view.findViewById(R.id.ed_register);
        editText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setCancelable(false);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        super.onStart();
    }
                                                                                    

  public static RegisterDialogFragment newInstance(String tittle) {
          RegisterDialogFragment fragment = new RegisterDialogFragment();
          Bundle bundle = new Bundle();
          bundle.putString("tittle", tittle);
          fragment.setArguments(bundle);
          fragment.setArguments(bundle);
          return fragment;
      }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                register();
                break;
            case R.id.btn_cancel:
                Toast.makeText(getActivity(),"退出",Toast.LENGTH_SHORT).show();
                Objects.requireNonNull(getDialog()).dismiss();
                break;
        }
    }
    private void register(){
        String user = mTilRegister.getEditText().getText().toString();
        String password = mTilPassword.getEditText().getText().toString();
        mTilRegister.setErrorEnabled(false);
        mTilPassword.setErrorEnabled(false);
        if(validateAccount(user)&&validatePassword(password)){
            SharedPreferences.Editor editorAccount = getActivity().getSharedPreferences("dataAccount", Context.MODE_PRIVATE).edit();
            SharedPreferences.Editor editorPassword = getActivity().getSharedPreferences("dataPassword", Context.MODE_PRIVATE).edit();
            editorAccount.putString("account",user);
            editorPassword.putString("password",password);
//            Toast.makeText(getActivity(), "账户："+user, Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), "密码："+password, Toast.LENGTH_SHORT).show();
            editorAccount.apply();
            editorPassword.apply();
            Toast.makeText(getActivity(),"注册成功",Toast.LENGTH_LONG).show();
            Objects.requireNonNull(getDialog()).dismiss();
        }else{
            Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
        }
    }
    private void showError(TextInputLayout textInputLayout,String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }
    private boolean validateAccount(String account){
        if(mTilRegister.getEditText().getText().toString().trim().equals("")){
//        if(TextUtils.isEmpty(mEtRegister.getText())){
            showError(mTilRegister,"用户名不能为空");
            return false;
        }
        return true;
    }
    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(mEtPassword.getText())) {
            showError(mTilPassword,"密码不能为空");
            return false;
        }

        if (password.length() < 6 || password.length() > 18) {
            showError(mTilPassword,"密码长度为6-18位");
            return false;
        }

        return true;
    }
}
