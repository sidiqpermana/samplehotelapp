package com.sidiq.myhotelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sidiq.myhotelapp.R;
import com.sidiq.myhotelapp.model.HotelItem;

import java.util.ArrayList;

/**
 * Created by Student2 on 2/10/2016.
 */
public class HotelAdapter extends BaseAdapter{
    private Activity mActivity;
    private ArrayList<HotelItem> listItem;

    public HotelAdapter(Activity mActivity, ArrayList<HotelItem> listItem){
        this.mActivity = mActivity;
        this.listItem = listItem;
    }

    @Override
    public int getCount() {
        return listItem.size();
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
            LayoutInflater inflater = (LayoutInflater)mActivity.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_hotel, null);
            holder = new ViewHolder();
            holder.txtName = (TextView)convertView.findViewById(R.id.txt_item_name);
            holder.txtAddress = (TextView)convertView.findViewById(R.id.txt_item_address);
            holder.txtStar = (TextView)convertView.findViewById(R.id.txt_item_star);
            holder.imgPhoto = (ImageView)convertView.findViewById(R.id.img_item_photo);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.txtName.setText(listItem.get(position).getName());
        holder.txtAddress.setText(listItem.get(position).getAddress());
        holder.txtStar.setText("Bintang : "+listItem.get(position).getStar());
        Glide.with(mActivity).load(listItem.get(position).getImage()).into(holder.imgPhoto);
        return convertView;
    }

    static class ViewHolder{
        TextView txtName, txtStar, txtAddress;
        ImageView imgPhoto;
    }
}
