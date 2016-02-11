package com.sidiq.myhotelapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sidiq.myhotelapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageSlideshowFragment extends Fragment {
    private String imageUrl;
    private ImageView imgSlideshow;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static Fragment newInstance(String url){
        ImageSlideshowFragment imageSlideshowFragment = new ImageSlideshowFragment();
        imageSlideshowFragment.setImageUrl(url);

        return imageSlideshowFragment;
    }

    public ImageSlideshowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_image_slideshow, container, false);
        imgSlideshow = (ImageView)view.findViewById(R.id.img_slideshow);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Glide.with(getActivity()).load(getImageUrl()).into(imgSlideshow);
    }
}
