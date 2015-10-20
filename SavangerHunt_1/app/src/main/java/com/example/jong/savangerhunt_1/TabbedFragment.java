package com.example.jong.savangerhunt_1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.util.Locale;


public class TabbedFragment extends android.support.v4.app.Fragment {

    public final static String TAG=TabbedFragment.class.getSimpleName();
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    private OnFragmentInteractionListener mListener;

    public static TabbedFragment newInstance() {
        return new TabbedFragment();
    }

    public TabbedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tabfrag, container, false);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        return v;

    }
     //TODO: make a separate file for sections pager adapter
     public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            Bundle args = new Bundle();
            android.support.v4.app.Fragment fragment;
            switch (position) {

                case 0:
                    fragment = new TabbedFragment_clip();
                    args.putInt(TabbedFragment_clip.ARG_SECTION_NUMBER, position + 1);
                    fragment.setArguments(args);
                    return fragment;

                case 1:
                    fragment = new TabbedFragment_map();
                    args.putInt(TabbedFragment_map.ARG_SECTION_NUMBER, position + 1);
                    fragment.setArguments(args);
                    return fragment;

            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
            }
            return null;
        }
    }
  //TODO: separate class - no static
    public static class TabbedFragment_clip extends android.support.v4.app.Fragment {

        public static final String ARG_SECTION_NUMBER = "1";
        public boolean hasarrived = false;
        private int mProgressStatus = 0; /**proximity value**/
        private ProgressBar mProgress;
        private Handler mHandler = new Handler();


        public TabbedFragment_clip() {
        }

        public void create_button(View v, String button){
            switch(button){
                //TODO:button name change to small case
                case("camera"):{
                    Button Camera = null;
                    Camera.setEnabled(hasarrived);
                    Camera = (Button) v.findViewById(R.id.Camera_button);
                }
            }

        }
        public int doWork() {
            int proximity=0;
            while (proximity <= 1000000) {
                proximity++;
                return (int) proximity;
            }
            return 100;
        }
        public void create_progressbar(View v){
            mProgress=(ProgressBar) v.findViewById(R.id.progressbar);
            new Thread(new Runnable() {
                @Override
                public void run() {
                while (mProgressStatus<100) {
                    mProgressStatus= doWork();

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
        public void create_videoview(View v){
            final VideoView mVideoView;
            final int videopos = 0;
            final ProgressDialog progressDialog;
            MediaController mediaControls=null;
            mVideoView = (VideoView) v.findViewById(R.id.video_view);
            if (mediaControls==null) {
                mediaControls = new MediaController(getActivity());
            }
            //TODO:pass media controller of the videoview
            mediaControls.setMediaPlayer(mVideoView);
//            progressDialog = new ProgressDialog(getActivity());
//            progressDialog.setTitle("Scavanger Hunt Clue Video");
//            progressDialog.setMessage("Loading...");
//            progressDialog.setCancelable(false);
//            progressDialog.show();
            String s3url = "https://s3.amazonaws.com/olin-mobile-proto/MVI_3140.3gp";

            try {
                mVideoView.setMediaController(mediaControls);
                mVideoView.setVideoURI(Uri.parse(s3url));
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            mVideoView.requestFocus();
            mVideoView.start();
//            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    progressDialog.dismiss();
//                    mVideoView.seekTo(videopos);
//                    if (videopos==0) {
//                        mVideoView.start();
//                    }else {
//                        mVideoView.pause();
//                    }
//                }
//            });

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.cliplayout, container, false);
            create_button(view,"Camera");
            create_progressbar(view);
            return view;
        }

    }
    //TODO:separate to another class
    public static class TabbedFragment_map extends android.support.v4.app.Fragment {
        public static final String ARG_SECTION_NUMBER = "2";
        public TabbedFragment_map() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.maplayout, container, false);
        }

    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
