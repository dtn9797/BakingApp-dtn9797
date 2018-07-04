package com.example.duynguyen.bakingapp_dtn9797;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.duynguyen.bakingapp_dtn9797.model.Recipe;

import java.util.ArrayList;

/**
 * Created by duynguyen on 6/29/18.
 */

public class DetailRecipeListActivity extends AppCompatActivity implements DetailRecipeListFragment.OnItemClickListener {

    public static String RECIPE_EXTRA = "recipe_extra";
    Recipe recipe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe_list);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        recipe = intent.getParcelableExtra(RECIPE_EXTRA);
        setTitle(recipe.getName());

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(DetailRecipeListFragment.LIST_NAMES_EXTRA, recipe.getShortDescriptionsFromSteps());
        DetailRecipeListFragment detailRecipeListFragment = new DetailRecipeListFragment();
        detailRecipeListFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .add(R.id.detail_recipe_list_fragment, detailRecipeListFragment)
                .commit();


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, getString(R.string.close_on_intent_error), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(int position) {
        if (position == 0){
            Intent ingredientsIntent = new Intent(DetailRecipeListActivity.this,IngredientsActivity.class);
            ingredientsIntent.putParcelableArrayListExtra(IngredientsActivity.INGREDIENTS_EXTRA, (ArrayList<? extends Parcelable>) recipe.getIngredients());
            startActivity(ingredientsIntent);
        }
        else {
            Intent stepIntent = new Intent(DetailRecipeListActivity.this,StepActivity.class);
            startActivity(stepIntent);
        }
    }
}
