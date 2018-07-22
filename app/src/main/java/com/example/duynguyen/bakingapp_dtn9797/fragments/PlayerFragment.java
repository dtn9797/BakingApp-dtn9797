package com.example.duynguyen.bakingapp_dtn9797.fragments;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duynguyen.bakingapp_dtn9797.R;
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
 * Created by duynguyen on 7/4/18.
 */

public class PlayerFragment extends Fragment {

    public static String VIDEO_URL_EXTRA = "video_url" ;
    public static String DESCRIPTION_EXTRA = "description" ;
    public static String PLAYBACK_POS_EXTRA = "pbpe" ;
    public static String CURRENT_WINDOW_EXTRA = "window" ;
    public static String PLAY_WHEN_READY_EXTRA = "play" ;


    private SimpleExoPlayer player;
    private PlayerView playerView;
    private TextView stepDescriptionTv;

    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;

    private String videoUrl;
    private String stepDescription;

    public PlayerFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player,container, false);

        playerView = view.findViewById(R.id.video_view);
        stepDescriptionTv = view.findViewById(R.id.step_instruction_tv);

        videoUrl = getArguments().getString(VIDEO_URL_EXTRA);
        stepDescription = getArguments().getString(DESCRIPTION_EXTRA);
        if(savedInstanceState!=null){
            playbackPosition = savedInstanceState.getLong(PLAYBACK_POS_EXTRA);
            currentWindow = savedInstanceState.getInt(CURRENT_WINDOW_EXTRA,0);
            playWhenReady = savedInstanceState.getBoolean(PLAY_WHEN_READY_EXTRA);
        }

        if(videoUrl.equals("")){
            playerView.setVisibility(View.GONE);
        }
        stepDescriptionTv.setText(stepDescription);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23 && !videoUrl.equals("")) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || player == null) && !videoUrl.equals("")) {
            initializePlayer();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void initializePlayer() {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getActivity()),
                    new DefaultTrackSelector(), new DefaultLoadControl());
            playerView.setPlayer(player);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);
        }
        MediaSource mediaSource = new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("bakingApp-dtn9797"))
                .createMediaSource(Uri.parse(videoUrl));
        player.prepare(mediaSource, false, false);
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        playbackPosition = player.getCurrentPosition();
        currentWindow = player.getCurrentWindowIndex();
        playWhenReady = player.getPlayWhenReady();
        outState.putLong(PLAYBACK_POS_EXTRA,playbackPosition);
        outState.putInt(CURRENT_WINDOW_EXTRA,currentWindow);
        outState.putBoolean(PLAY_WHEN_READY_EXTRA,playWhenReady);
    }



}
