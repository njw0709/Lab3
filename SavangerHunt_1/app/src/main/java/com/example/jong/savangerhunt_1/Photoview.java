package com.example.jong.savangerhunt_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 10/27/15.
 */
public class Photoview extends android.support.v4.app.Fragment {

    private String TAG = "PhotoView";
    private Uri uri;
    private ImageView imgView;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String mCurrentPhotoPath;
    private int currstage;
    private static String root = null;
    private static String imageFolderPath=null;
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
        createbutton(view, "Retake");
        return view;
    }

    public void createImageview(View v){
//        Bundle bundle = this.getArguments();
//        String uriString = bundle.getString("uri", null);
//        Log.d("create_imageview", uriString);
//        uri = Uri.parse(uriString);
        uri=null;
        imgView = (ImageView) v.findViewById(R.id.imageview);
        if(uri==null){
            imgView.setImageResource(R.drawable.ic_cam);
        }
        else{
            imgView.setImageURI(uri);
            imgView.setRotation(90);
        }
    }

    public void createbutton(View v, String button){
        switch (button) {
            case("Ok"): {
                Button ok = (Button) v.findViewById(R.id.okbutton);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        transitionToFragment(new ViewpagerContainer());
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
            case("Cancel"):{
                Button cancel = (Button) v.findViewById(R.id.cancelbutton);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        transitionToFragment(new ViewpagerContainer());
                    }
                });
            }
        }
    }
    public void captureImage(View view,int currstage){
        String imageName;
        Uri fileUri;
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);

        // fetching the root directory
        root = Environment.getExternalStorageDirectory().toString()
                + "/Scavangerhunt";

        // Creating folders for Image

        imageFolderPath = root;
        File imagesFolder = new File(imageFolderPath);
        if(!imagesFolder.exists()){
            imagesFolder.mkdirs();
        }

        // Generating file name
        imageName = "stage"+String.valueOf(currstage)+".jpg";
        // Creating image here
        File image = new File(imageFolderPath, imageName);
        fileUri = Uri.fromFile(image);
        dispatchTakePictureIntent(image);
    }

    public void dispatchTakePictureIntent(File photoFile) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestcode, int resultcode, )



    public void transitionToFragment(Fragment fragment) {
        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container_frame, fragment);
        transaction.commit();
    }


}
