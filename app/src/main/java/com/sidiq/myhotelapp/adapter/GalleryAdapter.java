package com.sidiq.myhotelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sidiq.myhotelapp.DetailHotelActivity;
import com.sidiq.myhotelapp.R;

/**
 * Created by Student2 on 2/11/2016.
 */
public class GalleryAdapter extends BaseAdapter {
    Activity mActivity;

    public GalleryAdapter(Activity mActivity){
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return DetailHotelActivity.imageUrls.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)mActivity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_gallery, null);
            holder = new ViewHolder();
            holder.imgGallery = (ImageView)convertView.findViewById(R.id.img_item_gallery);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        Glide.with(mActivity).load(DetailHotelActivity.imageUrls[position])
                .into(holder.imgGallery);
        return convertView;
    }

    static class ViewHolder{
        ImageView imgGallery;
    }
}
