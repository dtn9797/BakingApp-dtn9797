package com.example.duynguyen.bakingapp_dtn9797;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.duynguyen.bakingapp_dtn9797.model.Ingredient;
import com.example.duynguyen.bakingapp_dtn9797.utils.IngredientsAdapter;

import java.util.List;

/**
 * Created by duynguyen on 7/1/18.
 */

public class IngredientsActivity extends AppCompatActivity {
    public static String INGREDIENTS_EXTRA = "i_extra";
    List<Ingredient> mIngredients = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        Intent intent =  getIntent();
        if (intent == null) {
            closeOnError();
        }

        mIngredients = intent.getParcelableArrayListExtra(INGREDIENTS_EXTRA);


        RecyclerView rv = (RecyclerView)findViewById(R.id.ingredients_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(this, mIngredients);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(ingredientsAdapter);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, getString(R.string.close_on_intent_error), Toast.LENGTH_SHORT).show();
    }

}
