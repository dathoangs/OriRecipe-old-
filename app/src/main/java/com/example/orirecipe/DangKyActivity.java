package com.example.orirecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DangKyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
    }
    public void login(View view) {
        startActivity(new Intent(DangKyActivity.this, com.example.orirecipe.DangNhapActivity.class));
    }
}