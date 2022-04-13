package com.example.orirecipe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class FavAdapter extends RecyclerView.Adapter<FavViewHolder>{

    private Context favContext;
    private List<FoodData> mFoodList;

    public FavAdapter(Context favContext, List<FoodData> mFoodList) {
        this.favContext = favContext;
        this.mFoodList = mFoodList;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_recycler_row_item, parent, false);

        return new FavViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {

        System.out.println(holder.imageView);

        Glide.with(favContext)
                .load(mFoodList.get(position).getItemImage())
                .into(holder.imageView);

        holder.mTitle.setText(mFoodList.get(position).getItemName());
        holder.mDesc.setText(mFoodList.get(position).getItemDesc());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(favContext, DetailActivity.class);
                intent.putExtra("image", mFoodList.get(holder.getAdapterPosition()).getItemImage());
                intent.putExtra("description", mFoodList.get(holder.getAdapterPosition()).getItemDesc());
                favContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

}

class FavViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle, mDesc;
    CardView mCardView;

    public FavViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.favImage);
        mTitle = itemView.findViewById(R.id.favTitle);
        mDesc = itemView.findViewById(R.id.favDesc);

        mCardView = itemView.findViewById(R.id.favCardView);
    }
}
