package com.example.duynguyen.bakingapp_dtn9797.utils;

import com.example.duynguyen.bakingapp_dtn9797.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by duynguyen on 6/28/18.
 */

public interface RecipeClient {
    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> get_recipes();
}
