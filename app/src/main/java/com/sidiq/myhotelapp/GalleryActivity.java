package com.sidiq.myhotelapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sidiq.myhotelapp.adapter.GalleryAdapter;

public class GalleryActivity extends AppCompatActivity {
    private GridView gvGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getSupportActionBar().setTitle("Gallery Foto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gvGallery = (GridView)findViewById(R.id.gv_gallery);
        GalleryAdapter mGalleryAdapter = new GalleryAdapter(GalleryActivity.this);
        gvGallery.setAdapter(mGalleryAdapter);

        gvGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SlideshowActivity.toSlideshowActivity(GalleryActivity.this, position);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void toGalleryActivity(Activity mActivity){
        mActivity.startActivity(new Intent(mActivity, GalleryActivity.class));
    }
}
