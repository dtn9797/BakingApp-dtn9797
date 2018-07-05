package com.example.duynguyen.bakingapp_dtn9797;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.duynguyen.bakingapp_dtn9797.fragments.IngredientsFragment;
import com.example.duynguyen.bakingapp_dtn9797.model.Ingredient;
import com.example.duynguyen.bakingapp_dtn9797.utils.IngredientsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duynguyen on 7/1/18.
 */

public class IngredientsActivity extends AppCompatActivity {

    public static String INGREDIENTS_EXTRA = "i_extra";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        Intent intent =  getIntent();
        if (intent == null) {
            closeOnError();
        }

        List<Ingredient> ingredients = intent.getParcelableArrayListExtra(INGREDIENTS_EXTRA);

        FragmentManager fragmentManager = getSupportFragmentManager();
        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(IngredientsFragment.INGREDIENTS_EXTRA, (ArrayList<? extends Parcelable>) ingredients);
        ingredientsFragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                        .add(R.id.ingredients_fragment,ingredientsFragment)
                        .commit();

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, getString(R.string.close_on_intent_error), Toast.LENGTH_SHORT).show();
    }

}
