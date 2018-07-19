package com.example.duynguyen.bakingapp_dtn9797.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duynguyen.bakingapp_dtn9797.R;
import com.example.duynguyen.bakingapp_dtn9797.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duynguyen on 7/19/18.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeVH> {

    Context mContext;
    protected static ItemListener mItemListener;
    public List<Recipe> mData = new ArrayList<>();

    int[] drawableIds = {R.drawable.n_pie,R.drawable.brownies,R.drawable.yellow_cake,R.drawable.cheese_cake};

    public RecipesAdapter(Context mContext, ItemListener mItemListener ,List<Recipe> mData) {
        this.mContext = mContext;
        this.mItemListener = mItemListener;
        this.mData = mData;
    }

    public interface ItemListener {
        void onRecipeClicked(int position);
    }

    @NonNull
    @Override
    public RecipeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recipe_vh,parent,false);

        return new RecipeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeVH holder, int position) {
        mData.get(position).setImageId(drawableIds[position]);
        holder.setData(mData.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class RecipeVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView recipeIv;
        TextView recipeTv;
        int posVh;

        public RecipeVH(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            recipeIv = itemView.findViewById(R.id.recipe_iv);
            recipeTv = itemView.findViewById(R.id.recipe_tv);
        }

        public void setData (Recipe data,int pos){
            recipeTv.setText(data.getName());
            recipeIv.setImageResource(data.getImageId());
            posVh = pos;
        }

        @Override
        public void onClick(View v) {
            RecipesAdapter.mItemListener.onRecipeClicked(posVh);
        }
    }
}

