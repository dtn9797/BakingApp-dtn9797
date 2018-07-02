package com.example.duynguyen.bakingapp_dtn9797.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duynguyen.bakingapp_dtn9797.R;
import com.example.duynguyen.bakingapp_dtn9797.model.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {

    Context mContext;
    List<Ingredient> mItems;

    public IngredientsAdapter(Context mContext, List<Ingredient> mItems) {
        this.mContext = mContext;
        this.mItems = mItems;
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView recipeName;
        TextView recipeAmount;

        public IngredientViewHolder(View itemView) {
            super(itemView);

            recipeName = (TextView) itemView.findViewById(R.id.ingredient_name_tv);
            recipeAmount = (TextView) itemView.findViewById(R.id.ingredient_amount_tv);
        }

        public void setData(Ingredient data) {
            recipeName.setText(data.getIngredient());
            recipeName.setText(mContext.getString(R.string.ingredient_amount,data.getQuantity(),data.getMeasure()));
        }
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ingredient_vh,parent,false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.setData(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
