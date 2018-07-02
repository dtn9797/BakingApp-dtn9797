package com.example.duynguyen.bakingapp_dtn9797;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.duynguyen.bakingapp_dtn9797.model.Recipe;

/**
 * Created by duynguyen on 6/29/18.
 */

public class DetailRecipeListActivity extends AppCompatActivity {

    public static String RECIPE_EXTRA = "recipe_extra";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe_list);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        Recipe recipe = intent.getParcelableExtra(RECIPE_EXTRA);
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


}
