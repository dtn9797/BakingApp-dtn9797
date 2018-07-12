package com.example.duynguyen.bakingapp_dtn9797.widgets;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.duynguyen.bakingapp_dtn9797.MainActivity;
import com.example.duynguyen.bakingapp_dtn9797.R;
import com.example.duynguyen.bakingapp_dtn9797.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duynguyen on 7/11/18.
 */

public class WidgetUpdateService extends IntentService {

    public static final String ACTION_UPDATE_LIST_VIEW = "update_app_widget_list";
    public static final String RECIPE_NAME_KEY = "recipe name key";
    public static final String INGREDIENTS_KEY = "ingredients key";

    public WidgetUpdateService() {
        super("WidgetUpdateService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent !=null){
            final String action = intent.getAction();
            if (ACTION_UPDATE_LIST_VIEW.equals(action)){
                ArrayList<Ingredient> ingredients = intent.getParcelableArrayListExtra(INGREDIENTS_KEY);
                String recipeName = intent.getStringExtra(RECIPE_NAME_KEY);
                handleActionUpdateListView(recipeName,ingredients);
            }
        }
    }

    private void handleActionUpdateListView(String recipeName ,ArrayList<Ingredient> ingredients) {
        WidgetDataModel.saveIngredients(this, ingredients);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingAppWidget.class));

        BakingAppWidget.updateAppWidgets(this,appWidgetManager,appWidgetIds);

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.ingredients_list);
    }

    public static void startActionUpdateListView (Context context,String recipeName ,ArrayList<Ingredient> ingredients) {
        Intent intent = new Intent(context,WidgetUpdateService.class);
        intent.setAction(WidgetUpdateService.ACTION_UPDATE_LIST_VIEW);
        intent.putExtra(RECIPE_NAME_KEY,recipeName);
        intent.putExtra(INGREDIENTS_KEY,ingredients);
        context.startService(intent);
    }
}
