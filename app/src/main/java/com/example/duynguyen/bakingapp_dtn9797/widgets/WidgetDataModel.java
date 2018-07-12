package com.example.duynguyen.bakingapp_dtn9797.widgets;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.duynguyen.bakingapp_dtn9797.model.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duynguyen on 7/11/18.
 */

public class WidgetDataModel {
    public static String INGREDIENTS_KEY = "in";

    public static void saveIngredients (Context context, ArrayList<Ingredient> ingredients){

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ingredients);
        prefsEditor.putString(INGREDIENTS_KEY, json);
        prefsEditor.commit();
    }

    public static ArrayList<Ingredient> getArrayListIngredients(String key,Activity activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Ingredient>>() {}.getType();
        return gson.fromJson(json, type);
    }

}
