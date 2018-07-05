package com.example.duynguyen.bakingapp_dtn9797.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duynguyen.bakingapp_dtn9797.R;
import com.example.duynguyen.bakingapp_dtn9797.model.Ingredient;
import com.example.duynguyen.bakingapp_dtn9797.utils.IngredientsAdapter;

import java.util.List;

/**
 * Created by duynguyen on 7/4/18.
 */

public class IngredientsFragment extends Fragment {

    public static String INGREDIENTS_EXTRA = "i_extra";
    List<Ingredient> mIngredients = null;

    public IngredientsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ingredients,container,false);

        mIngredients = getArguments().getParcelableArrayList(INGREDIENTS_EXTRA);

        RecyclerView rv = (RecyclerView)view.findViewById(R.id.ingredients_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(getActivity(), mIngredients);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(ingredientsAdapter);

        return view;
    }
}
