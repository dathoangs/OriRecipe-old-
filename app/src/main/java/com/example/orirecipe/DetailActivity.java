package com.example.orirecipe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvDesc_detail;
    ImageView ivImage_detail;
    Button btnFav;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    private ValueEventListener eventListener;
    private DatabaseReference userReference;
    private String key, itemId;
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvDesc_detail = (TextView) findViewById(R.id.tvDesc_detail);
        ivImage_detail = (ImageView) findViewById(R.id.ivImage_detail);
//        btnFav = (Button) findViewById(R.id.btnAddFav);
//        btnFav.setOnClickListener(this);
        userReference = FirebaseDatabase.getInstance().getReference("Users");

        firebaseAuth = FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){

            tvDesc_detail.setText(bundle.getString("description"));
            itemId = bundle.getString("id");
            Glide.with(this)
                    .load(bundle.getString("image"))
                    .into(ivImage_detail);
        }
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btnAddFav:{
//
//                if (firebaseAuth.getCurrentUser() == null){
//                    Toast.makeText(DetailActivity.this, "Bạn chưa đăng nhập", Toast.LENGTH_LONG).show();
//                } else {
//                    key = firebaseAuth.getCurrentUser().getUid();
//                    favUpd(itemId);
//                }
//
//            }
//        }
    }

    void favUpd (String string){

//        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                user = snapshot.child(key).getValue(User.class);
//
//                if (user.favRecipe.contains(string)){
//                    Toast.makeText(DetailActivity.this, "Bạn đã yêu thích công thức này rồi", Toast.LENGTH_LONG).show();
//                } else {
//                    user.favRecipe.add(string);
//
//                    userReference.child(key)
//                            .child("favRecipe").setValue(user.favRecipe)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()){
//                                        Toast.makeText(DetailActivity.this,
//                                                "Yêu thích công thức thành công",
//                                                Toast.LENGTH_LONG).show();
//                                    } else {
//                                        Toast.makeText(DetailActivity.this,
//                                                "Yêu thích công thức không thành công!",
//                                                Toast.LENGTH_LONG).show();
//                                    }
//                                }
//                            });
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(DetailActivity.this, "Yêu thích công thức không thành công", Toast.LENGTH_LONG).show();
//            }
//        });

    }
}