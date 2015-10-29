package com.example.jong.savangerhunt_1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

/**
 * Created by root on 10/22/15.
 */
public class TabbedFragment_clip extends android.support.v4.app.Fragment {

    public static final String ARG_SECTION_NUMBER = "1";
    private StageData stageData = new StageData(this.getActivity());
    private VideoView mVideoView;
    private TextView textview;
    private TextView stageprogress;
    private ImageView imageView;


    public TabbedFragment_clip() {
    }

    public void create_videoview(View v) {
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
        //TODO:setvideourlwith stagedata
        String s3url = "https://s3.amazonaws.com/olin-mobile-proto/MVI_3140.3gp";
        mVideoView.setVideoURI(Uri.parse(s3url));
        mVideoView.requestFocus();
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
        create_videoview(view);
        textview = (TextView) view.findViewById(R.id.stage_text);
        textview.setText("    Stage # ".concat(String.valueOf(stageData.getVisiblestage())));
        stageprogress = (TextView) view.findViewById(R.id.Stage_status);
        imageView = (ImageView) view.findViewById(R.id.completed_image);
        if(stageData.getCurrstage()>stageData.getVisiblestage()){
            stageprogress.setVisibility(View.GONE);
        }
        else if(stageData.getCurrstage()==stageData.getVisiblestage()){
            imageView.setVisibility(View.GONE);
        }
        return view;
    }

    public void transitionToFragment(Fragment fragment) {
        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.container_frame, fragment);
        transaction.commit();
    }
    public void updateclipview(int stage){
//        String newvideourl = stageData.getVideoURI(stage);
//        mVideoView.setVideoURI(Uri.parse(newvideourl));
        textview.setText("    Stage # ".concat(String.valueOf(stage)));
        if(stageData.getCurrstage()>stageData.getVisiblestage()){;
            stageprogress.setVisibility(View.GONE);
        }
        else if(stageData.getCurrstage()==stageData.getVisiblestage()){
            imageView.setVisibility(View.GONE);
        }
    }

}