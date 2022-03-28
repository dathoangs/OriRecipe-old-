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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView mRecyclerView;
    List <FoodData> mFoodList;
    FoodData mFoodData;
    ProgressDialog progressDialog;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    MyAdapter myAdapter;
    EditText etSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnAdd = (ImageButton) findViewById(R.id.btnUpload);
        ImageButton btnUser = (ImageButton) findViewById(R.id.btnUser);

        btnAdd.setOnClickListener(this);
        btnUser.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        etSearchText = (EditText) findViewById(R.id.etSearchText);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang tải...");

        mFoodList = new ArrayList<>();

        myAdapter = new MyAdapter(MainActivity.this, mFoodList);
        mRecyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");

        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mFoodList.clear();

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    FoodData foodData = dataSnapshot.getValue(com.example.orirecipe.FoodData.class);
                    mFoodList.add(foodData);

                }

                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });

        etSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());

            }
        });
    }

    public void filter(String string){
        ArrayList<FoodData> filterList = new ArrayList<>();

        for (FoodData item: mFoodList){
            if (item.getItemName().toLowerCase().contains(string.toString())){
                filterList.add(item);
            }
        }

        myAdapter.filteredList(filterList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnUpload:{
                startActivity(new Intent(this, com.example.orirecipe.RecipeUpload.class));
                break;
            }

            case R.id.btnUser:{
                startActivity(new Intent(this, com.example.orirecipe.DangNhapActivity.class));
                break;
            }
        }

    }
}