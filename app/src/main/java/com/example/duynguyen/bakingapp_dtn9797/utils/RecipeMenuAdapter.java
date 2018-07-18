package com.example.duynguyen.bakingapp_dtn9797.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duynguyen.bakingapp_dtn9797.R;
import com.example.duynguyen.bakingapp_dtn9797.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duynguyen on 6/28/18.
 */

public class RecipeMenuAdapter extends ArrayAdapter {
    private Context mContext;
    private int mLayoutResourceId;
    public List<Recipe> mData = new ArrayList<>();

    public RecipeMenuAdapter(Context context, int layoutResourceId, List<Recipe> data) {
        super(context, layoutResourceId, data);
        mContext = context;
        mLayoutResourceId = layoutResourceId;
        mData = data;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;
        Recipe currentRecipe = (Recipe) getItem(position);

        if (convertView == null) {
            // If it's not recycled, initialize some attributes
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mLayoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = convertView.findViewById(R.id.recipe_grid_name);
            holder.image = convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageTitle.setText(currentRecipe.getName());
        int[] drawableIds = {R.drawable.n_pie,R.drawable.brownies,R.drawable.yellow_cake,R.drawable.cheese_cake};
        holder.image.setImageResource(drawableIds[position]);

        return convertView;
    }

}
