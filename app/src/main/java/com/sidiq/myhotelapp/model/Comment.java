package com.sidiq.myhotelapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Student2 on 2/12/2016.
 */
public class Comment extends BaseModel{
    @SerializedName("comment")
    public ArrayList<CommentItem> listComment = new ArrayList<>();
}
