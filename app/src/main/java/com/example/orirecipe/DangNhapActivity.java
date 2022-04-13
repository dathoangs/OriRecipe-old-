package com.example.orirecipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail, etPass;
    private Button btnDangnhap;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        btnDangnhap = (Button) findViewById(R.id.buttondn);
        btnDangnhap.setOnClickListener(this);

        etEmail = (EditText) findViewById(R.id.edittextuser);
        etPass = (EditText) findViewById(R.id.editpassword);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void register(View view) {
        startActivity(new Intent(DangNhapActivity.this,DangKyActivity.class));
    }

    private void userLogin(){
        String email = etEmail.getText().toString().trim(),
                pass = etPass.getText().toString().trim();

        if (email.isEmpty()){
            etEmail.setError("Chưa nhập Email!");
            etEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Email không đúng định dạng!");
            etEmail.requestFocus();
            return;
        }

        if (pass.isEmpty() || pass.length() < 6){
            etPass.setError("Điền lại mật khẩu!");
            etPass.requestFocus();
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Vui lòng đợi...");

        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //Chuyển sang activity User Profile
                    //Temporary code for testing
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập không thành công.\nVui lòng thử lại.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttondn:{
                userLogin();
                startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                finish();
                break;
            }
        }
    }
}