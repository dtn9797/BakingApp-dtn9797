/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
* limitations under the License.
 */
package com.example.duynguyen.bakingapp_dtn9797;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.duynguyen.bakingapp_dtn9797.fragments.PlayerFragment;
import com.example.duynguyen.bakingapp_dtn9797.model.Step;

import java.util.List;

/**
 * A fullscreen activity to play audio or video streams.
 */
public class StepActivity extends AppCompatActivity {

    private static String TAG = StepActivity.class.getSimpleName();
    public static String STEPS_EXTRA = "steps";
    public static String POS_EXTRA = "pos";
    public static String RECIPE_NAME_EXTRA = "name";

    private Toolbar toolBar;
    private Button nextButton;
    private Button previousButton;

    private FragmentManager fragmentManager;
    private String recipeName = "";
    private int currentPos;
    private int defaultPos = 0;
    private List<Step> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_step);

        toolBar = (Toolbar) findViewById(R.id.toolBar);
        nextButton = (Button)findViewById(R.id.next_button);
        previousButton = (Button)findViewById(R.id.previous_button);
        fragmentManager = getSupportFragmentManager();



        Intent intent = getIntent();
        if (intent == null){
            closeOnError();
        }
        currentPos = intent.getIntExtra(POS_EXTRA,defaultPos);
        steps = intent.getParcelableArrayListExtra(STEPS_EXTRA);
        recipeName = intent.getStringExtra(RECIPE_NAME_EXTRA);

        toolBar.setTitle(recipeName);
        toolBar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Back button is clicked",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        setEnablePreviousNextButton();

        populatePlayerView();

    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        Toast.makeText(getApplicationContext(),"Back button is clicked",Toast.LENGTH_LONG).show();
//        return true;
//    }

    private void populatePlayerView(){

        String url0 = steps.get(currentPos).getVideoURL();
        String url1 = steps.get(currentPos).getThumbnailURL();
        String videoUrl = (url1.equals(""))?url0:url1;
        String stepDescription = steps.get(currentPos).getDescription();

        Bundle bundle = new Bundle();
        bundle.putString(PlayerFragment.DESCRIPTION_EXTRA,stepDescription);
        bundle.putString(PlayerFragment.VIDEO_URL_EXTRA,videoUrl);
        PlayerFragment playerFragment = new PlayerFragment();
        playerFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.player_fragment, playerFragment)
                .commit();

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, getString(R.string.close_on_intent_error), Toast.LENGTH_SHORT).show();
    }


    public void nextOnClick (View view){
        Toast.makeText(this,"Next",Toast.LENGTH_SHORT).show();
        currentPos++;
        setEnablePreviousNextButton();
        populatePlayerView();
    }

    public void previousOnClick (View view){
        Toast.makeText(this,"Previous",Toast.LENGTH_SHORT).show();
        currentPos--;
        setEnablePreviousNextButton();
        populatePlayerView();
    }

    public void setEnablePreviousNextButton (){
        if (currentPos == steps.size()-1){
            nextButton.setEnabled(false);
        }
        else if (currentPos == 0){
            previousButton.setEnabled(false);
        }
        else{
            nextButton.setEnabled(true);
            previousButton.setEnabled(true);
        }
    }

}
