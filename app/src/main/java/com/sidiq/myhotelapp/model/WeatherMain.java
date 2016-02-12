package com.sidiq.myhotelapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Student2 on 2/12/2016.
 */
public class WeatherMain {
    @SerializedName("temp")
    @Expose
    public double temp;
    @SerializedName("pressure")
    @Expose
    public double pressure;
    @SerializedName("humidity")
    @Expose
    public double humidity;
    @SerializedName("temp_min")
    @Expose
    public double tempMin;
    @SerializedName("temp_max")
    @Expose
    public double tempMax;

}
