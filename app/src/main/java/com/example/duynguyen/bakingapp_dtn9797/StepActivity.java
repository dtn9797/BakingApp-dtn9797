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

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duynguyen.bakingapp_dtn9797.fragments.DetailRecipeListFragment;
import com.example.duynguyen.bakingapp_dtn9797.fragments.PlayerFragment;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * A fullscreen activity to play audio or video streams.
 */
public class StepActivity extends AppCompatActivity {

    private String TAG = StepActivity.class.getSimpleName();


    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recipe_step);

        toolBar = (Toolbar) findViewById(R.id.toolBar);

        toolBar.setTitle("Recipe Name");
        toolBar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Back button is clicked",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        String stepDescription = "6. Scrape down the sides of the pan. Add in the eggs one at a time, beating each one on medium-low speed just until incorporated. Scrape down the sides and bottom of the bowl. Add in both egg yolks and beat until just incorporated.";
        String videoUrl = "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffdb72_5-mix-vanilla-cream-together-cheesecake/5-mix-vanilla-cream-together-cheesecake.mp4";

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString(PlayerFragment.DESCRIPTION_EXTRA,stepDescription);
        bundle.putString(PlayerFragment.VIDEO_URL_EXTRA,videoUrl);
        PlayerFragment playerFragment = new PlayerFragment();
        playerFragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .add(R.id.player_fragment, playerFragment)
                .commit();

    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        Toast.makeText(getApplicationContext(),"Back button is clicked",Toast.LENGTH_LONG).show();
//        return true;
//    }


    public void nextOnClick (View view){

    }

    public void previousOnClick (View view){

    }

}
