package com.sidiq.myhotelapp.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Student2 on 2/12/2016.
 */
public class Login extends BaseModel{
    @Nullable
    @SerializedName("data")
    public User mUser;
}
