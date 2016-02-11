package com.sidiq.myhotelapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sidiq.myhotelapp.adapter.SlideshowFragmentAdapter;

public class SlideshowActivity extends AppCompatActivity {
    private int imagePosition;
    public static String KEY_POSITION = "position";

    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        imagePosition = getIntent().getIntExtra(KEY_POSITION, 0);
        mPager = (ViewPager)findViewById(R.id.pager);
        getSupportActionBar().hide();

        SlideshowFragmentAdapter mSlideshowFragmentAdapter =
                new SlideshowFragmentAdapter(getSupportFragmentManager());
        mPager.setAdapter(mSlideshowFragmentAdapter);
        mPager.setCurrentItem(imagePosition);
    }

    public static void toSlideshowActivity(Activity mActivity, int position){
        Intent mIntent = new Intent(mActivity, SlideshowActivity.class);
        mIntent.putExtra(KEY_POSITION, position);
        mActivity.startActivityForResult(mIntent, 0);
    }
}
