package com.example.duynguyen.bakingapp_dtn9797;

import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.GridView;

import com.example.duynguyen.bakingapp_dtn9797.model.Recipe;
import com.example.duynguyen.bakingapp_dtn9797.utils.RecipeClient;
import com.example.duynguyen.bakingapp_dtn9797.utils.RecipeMenuAdapter;
import com.example.duynguyen.bakingapp_dtn9797.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity {

    static private String Tag = RecipeActivity.class.getSimpleName();

    @BindView(R.id.menu_toolbar)
    Toolbar menuToolbar;
    @BindView(R.id.recipe_grid_view)
    GridView recipeGv;
    RecipeMenuAdapter mRecipeMenuAdapter;
    List<Recipe> mRecipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
        }

        mRecipeMenuAdapter = new RecipeMenuAdapter(RecipeActivity.this, R.layout.grid_item_layout, mRecipes);
        recipeGv.setAdapter(mRecipeMenuAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }

    public void loadRecipesData () {
        RecipeClient client = new RetrofitClient().getClient().create(RecipeClient.class);
        Call<List<Recipe>> call = client.get_recipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                mRecipes = response.body();
                mRecipeMenuAdapter.setData(mRecipes);
            }
            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                //Show alert dialog
                Log.e("Error", "Error in retrofit");
                AlertDialog.Builder dialog = new AlertDialog.Builder(RecipeActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle(getString(R.string.connection_error_title));
                dialog.setMessage(getString(R.string.connection_error));
                dialog.setPositiveButton(getString(R.string.reload_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        loadRecipesData();
                    }
                });
                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });
    }

}
