package com.example.duynguyen.bakingapp_dtn9797;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AndroidException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.duynguyen.bakingapp_dtn9797.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by duynguyen on 6/29/18.
 */

public class DetailRecipeListFragment extends Fragment {

    public static String LIST_NAMES_EXTRA = "names_extra";

    public DetailRecipeListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_detail_recipe_list,container,false);

        ListView listView = (ListView)rootView.findViewById(R.id.lv);
        final ArrayList<String> names_list = getArguments().getStringArrayList(LIST_NAMES_EXTRA);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.simple_list_item_1, (List<String>) names_list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),names_list.get(position) + "is clicked.",Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
