package com.sidiq.myhotelapp.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Student2 on 2/12/2016.
 */
public class Weather {
    @Nullable
    @SerializedName("main")
    public WeatherMain mWeatherMain;

    @Nullable
    @SerializedName("weather")
    public ArrayList<WeatherItem> listWeather = new ArrayList<>();

    @SerializedName("cod")
    public int code;

    @SerializedName("name")
    public String city;
}
