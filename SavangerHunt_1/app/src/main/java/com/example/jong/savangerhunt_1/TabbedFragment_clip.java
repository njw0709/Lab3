package com.example.jong.savangerhunt_1;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

/**
 * Created by root on 10/22/15.
 */
public class TabbedFragment_clip extends android.support.v4.app.Fragment {

    public static final String ARG_SECTION_NUMBER = "1";
    public boolean hasarrived = false;
    private int mProgressStatus = 0;
    /**
     * proximity value
     **/
    private ProgressBar mProgress;
    private Handler mHandler = new Handler();


    public TabbedFragment_clip() {
    }

    public void create_button(View v, String button) {
        switch (button) {
            //TODO:button name change to small case
            case ("camera"): {
                Button camera = (Button) v.findViewById(R.id.Camera_button);
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }

    }

    public int doWork() {
        int proximity = 0;
        while (proximity <= 1000000) {
            proximity++;
            return (int) proximity;
        }
        return 100;
    }

    public void create_progressbar(View v) {
        mProgress = (ProgressBar) v.findViewById(R.id.progressbar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus = doWork();

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }
                    });
                }
            }
        });
    }

    public void create_videoview(View v) {
        final VideoView mVideoView;
        final int videopos = 0;
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Scavanger Hunt Clue Video");
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
        MediaController mediaControls = new MediaController(getContext());
        mVideoView = (VideoView) v.findViewById(R.id.video_view);
        mVideoView.setMediaController(mediaControls);
        String s3url = "https://s3.amazonaws.com/olin-mobile-proto/MVI_3140.3gp";
        mVideoView.setVideoURI(Uri.parse(s3url));
        mVideoView.requestFocus();
        mVideoView.setZOrderOnTop(true);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                mVideoView.start();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cliplayout, container, false);
        create_button(view, "Camera");
        create_progressbar(view);
        create_videoview(view);
        return view;
    }
}