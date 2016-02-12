package com.sidiq.myhotelapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Student2 on 2/12/2016.
 */
public class CommentItem {
    @SerializedName("comment")
    public String comment;

    @SerializedName("tanggal_comment")
    public String tanggalComment;

    @SerializedName("nama_user")
    public String namaUser;
}
