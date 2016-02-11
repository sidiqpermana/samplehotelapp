package com.sidiq.myhotelapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sidiq.myhotelapp.fragment.ImageSlideshowFragment;

public class ImageDetailActivity extends AppCompatActivity {
    private String imageUrl;
    public static String KEY_IMAGE_URL = "imageUrl";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        imageUrl = getIntent().getStringExtra(KEY_IMAGE_URL);

        getSupportActionBar().hide();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ImageSlideshowFragment mFragment = new ImageSlideshowFragment();
        mFragment.setImageUrl(imageUrl);

        fragmentTransaction.replace(R.id.frame_image_container, mFragment,
                ImageSlideshowFragment.class.getSimpleName());

        fragmentTransaction.commit();
    }

    public static void toImageDetailActivity(Activity activity, String url){
        Intent intent = new Intent(activity, ImageDetailActivity.class);
        intent.putExtra(KEY_IMAGE_URL, url);
        activity.startActivityForResult(intent, 0);
    }
}
