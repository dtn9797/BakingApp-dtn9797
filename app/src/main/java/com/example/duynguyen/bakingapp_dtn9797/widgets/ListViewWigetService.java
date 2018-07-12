package com.example.duynguyen.bakingapp_dtn9797.widgets;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.duynguyen.bakingapp_dtn9797.MainActivity;
import com.example.duynguyen.bakingapp_dtn9797.R;
import com.example.duynguyen.bakingapp_dtn9797.model.Ingredient;
import com.example.duynguyen.bakingapp_dtn9797.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duynguyen on 7/11/18.
 */

public class ListViewWigetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new AppWidgetListView(getApplicationContext());
    }
}

 class AppWidgetListView implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    List<Ingredient> ingredients = new ArrayList<>();

     public AppWidgetListView(Context mContext){
         this.mContext = mContext;
     }

     @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
         Recipe recipe = WidgetDataModel.getRecipe(mContext);
         ingredients = recipe.getIngredients();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
         RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.list_item_widget);

         remoteViews.setTextViewText(R.id.ingredient_name_widget_tv,ingredients.get(position).getIngredient());
         remoteViews.setTextViewText(R.id.ingredient_amount_widget_tv,mContext.getResources().getString(R.string.ingredient_amount,ingredients.get(position).getQuantity(),ingredients.get(position).getMeasure()));

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}