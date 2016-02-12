package com.sidiq.myhotelapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Student2 on 2/12/2016.
 */
public class User {
    @SerializedName("id")
    public String idUser;

    @SerializedName("nama")
    public String nama;

    @SerializedName("email")
    public String email;
}
