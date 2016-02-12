package com.sidiq.myhotelapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Student2 on 2/12/2016.
 */
public class WeatherItem {
    @SerializedName("main")
    public String weatherName;

    @SerializedName("icon")
    public String icon;
}
