package com.example.orirecipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavouriteRecipe extends AppCompatActivity implements View.OnClickListener {

    RecyclerView mRecyclerView;
    List <FoodData> mFoodList;
    //FoodData mFoodData;
    ProgressDialog progressDialog;

    private DatabaseReference databaseReference, userReference;
    private ValueEventListener eventListener, eventListener1;
    private User user = new User();
    FavAdapter myAdapter;
    EditText etSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_recipe);

        mRecyclerView = (RecyclerView) findViewById(R.id.favrecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(FavouriteRecipe.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang tải...");

        mFoodList = new ArrayList<>();

        myAdapter = new FavAdapter(FavouriteRecipe.this, mFoodList);
        mRecyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");
        userReference = FirebaseDatabase.getInstance().getReference("Users");

        progressDialog.show();

        eventListener = userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null){
                    user = snapshot.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(User.class);
                    eventListener1 = databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot1) {
                            mFoodList.clear();
                            List <String> list = new ArrayList();

                            //Lấy dữ liệu từ user add vào list
                            //*** Code Here ***
                            list = user.favRecipe;
                            list.remove(0);
                            //*****************
                            //System.out.println(list);

                            //tmp code for testing
                            //list.add("-MzwPIi4Tyi0d3oA5FE-");

                            for (String string : list){
                                FoodData foodData = snapshot1.child(string).getValue(com.example.orirecipe.FoodData.class);
                                mFoodList.add(foodData);
                            }

                            myAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    progressDialog.dismiss();
                    startActivity(new Intent(FavouriteRecipe.this, DangNhapActivity.class));
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });

/*        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnUpload:{
                startActivity(new Intent(this, com.example.orirecipe.RecipeUpload.class));
                break;
            }

            case R.id.btnUser:{
                startActivity(new Intent(this, com.example.orirecipe.DangKyActivity.class));
                break;
            }
        }

    }
}