package com.example.orirecipe;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUsername, tvUseremail;
    private DatabaseReference userReference;
    private FirebaseAuth firebaseAuth;
    private String key;
    private ValueEventListener eventListener;
    private User user;
    private LinearLayout layoutLogout;
    private RelativeLayout notLogin;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        tvUseremail = (TextView) findViewById(R.id.tvUseremail);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        layoutLogout = (LinearLayout) findViewById(R.id.layoutLogout);
        layoutLogout.setOnClickListener(this);

        notLogin = (RelativeLayout) findViewById(R.id.notLogin);
//        notLogin.setOnClickListener(this);

        userReference = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            key = firebaseAuth.getCurrentUser().getUid();
        }

        getInfo();

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layoutLogout:{
                logout();
                getInfo();
                finish();
                break;
            }
        }
    }

    private void logout() {
        firebaseAuth.signOut();
    }

    private void getInfo() {
        if (firebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(this, DangNhapActivity.class));
            finish();
        } else {
            eventListener = userReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user = snapshot.child(key).getValue(User.class);
                    tvUseremail.setText(user.email);
                    tvUsername.setText(user.name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ProfileActivity.this, "Chưa đăng nhập", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
