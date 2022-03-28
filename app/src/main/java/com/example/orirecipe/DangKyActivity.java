package com.example.orirecipe;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName, etEmail, etPassword, etPass_check;
    private Button btnDk;
    private View tvLogin;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        mAuth = FirebaseAuth.getInstance();

        etEmail = (EditText) findViewById(R.id.etEmail);
        etName = (EditText) findViewById(R.id.etuser);
        etPassword = (EditText) findViewById(R.id.etpassword);
        etPass_check = (EditText) findViewById(R.id.etpasswordcheck);

        btnDk = (Button) findViewById(R.id.btnDk);
        tvLogin = findViewById(R.id.login);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:{
                startActivity(new Intent(DangKyActivity.this, com.example.orirecipe.DangNhapActivity.class));
                break;
            }

            case R.id.btnDk:{
                registerUser();
                break;
            }

        }
    }

    private void registerUser() {
        String email = etEmail.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();
        String pass_chk = etPass_check.getText().toString().trim();

        if (email.isEmpty()){
            etEmail.setError("Bạn chưa nhập Email!");
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Email không đúng định dạng!");
            etEmail.requestFocus();
            return;
        }

        if (name.isEmpty()){
            etName.setError("Bạn chưa nhập Tên!");
            etName.requestFocus();
            return;
        }

        if (pass.isEmpty()){
            etPassword.setError("Bạn chưa nhập Mật Khẩu!");
            etPassword.requestFocus();
            return;
        }

        if (pass != pass_chk){
            etPass_check.setError("Không giống mật khẩu ban đầu!");
            etPass_check.requestFocus();
            return;
        }

    }


}