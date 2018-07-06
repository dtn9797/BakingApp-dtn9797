package com.example.duynguyen.bakingapp_dtn9797;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.duynguyen.bakingapp_dtn9797.fragments.ListFragment;
import com.example.duynguyen.bakingapp_dtn9797.model.Recipe;

import java.util.ArrayList;

/**
 * Created by duynguyen on 6/29/18.
 */

public class DetailActivity extends AppCompatActivity implements ListFragment.OnItemClickListener {

    public static String RECIPE_EXTRA = "recipe_extra";

    Recipe recipe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(savedInstanceState == null) {
            Intent intent = getIntent();
            if (intent == null) {
                closeOnError();
            }

            recipe = intent.getParcelableExtra(RECIPE_EXTRA);

            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(ListFragment.LIST_NAMES_EXTRA, recipe.getShortDescriptionsFromSteps());
            ListFragment listFragment = new ListFragment();
            listFragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .add(R.id.detail_recipe_list_fragment, listFragment)
                    .commit();
        } else {
            recipe = savedInstanceState.getParcelable(RECIPE_EXTRA);
        }

        setTitle(recipe.getName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, getString(R.string.close_on_intent_error), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(int position) {
        if (position == 0){
            Intent ingredientsIntent = new Intent(DetailActivity.this,IngredientsActivity.class);
            ingredientsIntent.putParcelableArrayListExtra(IngredientsActivity.INGREDIENTS_EXTRA, (ArrayList<? extends Parcelable>) recipe.getIngredients());
            startActivity(ingredientsIntent);
        }
        else {
            Intent stepIntent = new Intent(DetailActivity.this,StepActivity.class);
            stepIntent.putParcelableArrayListExtra(StepActivity.STEPS_EXTRA, (ArrayList<? extends Parcelable>) recipe.getSteps());
            stepIntent.putExtra(StepActivity.POS_EXTRA,position-1);
            stepIntent.putExtra(StepActivity.RECIPE_NAME_EXTRA,recipe.getName());
            startActivity(stepIntent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECIPE_EXTRA,recipe);
    }
}
