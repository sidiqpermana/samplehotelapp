package com.sidiq.myhotelapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sidiq.myhotelapp.R;
import com.sidiq.myhotelapp.model.CommentItem;

import java.util.ArrayList;

/**
 * Created by Student2 on 2/12/2016.
 */
public class CommentAdapter extends BaseAdapter {
    Activity mActivity;
    ArrayList<CommentItem> listItems;

    public CommentAdapter(Activity mActivity, ArrayList<CommentItem> listItems){
        this.listItems = listItems;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return listItems.size();
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
            convertView = inflater.inflate(R.layout.item_list_comment, null);
            holder = new ViewHolder();
            holder.txtName = (TextView)convertView.findViewById(R.id.txt_item_username);
            holder.txtComment = (TextView)convertView.findViewById(R.id.txt_item_comment);
            holder.txtDate = (TextView)convertView.findViewById(R.id.txt_item_date);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.txtComment.setText(listItems.get(position).comment);
        holder.txtName.setText("Oleh "+listItems.get(position).namaUser);
        holder.txtDate.setText("Pada "+listItems.get(position).tanggalComment);
        return convertView;
    }

    static class ViewHolder{
        TextView txtName, txtComment, txtDate;
    }
}
