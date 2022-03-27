package com.example.orirecipe;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    TextView tvDesc_detail;
    ImageView ivImage_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvDesc_detail = (TextView) findViewById(R.id.tvDesc_detail);
        ivImage_detail = (ImageView) findViewById(R.id.ivImage_detail);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            tvDesc_detail.setText(bundle.getString("description"));
            Glide.with(this)
                    .load(bundle.getString("image"))
                    .into(ivImage_detail);
        }
    }
}