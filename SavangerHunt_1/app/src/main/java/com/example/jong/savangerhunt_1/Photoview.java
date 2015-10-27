package com.example.jong.savangerhunt_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by root on 10/27/15.
 */
public class Photoview extends android.support.v4.app.Fragment {

    private String TAG = "PhotoView";
    private Uri uri;
    private ImageView imgView;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public Photoview() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.photoview, container, false);
        createImageview(view);
        createbutton(view, "Ok");
        createbutton(view,"Retake");
        return view;
    }

    public void createImageview(View v){
        Bundle bundle = this.getArguments();
        String uriString = bundle.getString("uri", null);
        Log.d("create_imageview", uriString);
        uri = Uri.parse(uriString);
        imgView = (ImageView)v.findViewById(R.id.imageView);
        imgView.setImageURI(uri);
        imgView.setRotation(90);
    }

    public void createbutton(View v, String button){
        switch (button) {
            case("Ok"): {
                Button ok = (Button) v.findViewById(R.id.okbutton);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
            case("Retake"):{
                Button retake = (Button) v.findViewById(R.id.retakebutton);
                retake.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dispatchTakePictureIntent();

                    }
                });
            }
        }
    }

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }
    public void transitionToFragment(Fragment fragment) {
        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.viewpager, fragment);
        transaction.commit();
    }


}
