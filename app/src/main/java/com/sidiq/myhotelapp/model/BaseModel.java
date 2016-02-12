package com.sidiq.myhotelapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Student2 on 2/12/2016.
 */
public class BaseModel {
    @SerializedName("success")
    public int success;

    @SerializedName("message")
    public String message;
}
