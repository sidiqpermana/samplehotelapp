package com.sidiq.myhotelapp.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Student2 on 2/11/2016.
 */
public class UserPreference {
    private Context mContext;

    private String KEY_EMAIL = "email";
    private String KEY_ID = "id";
    private String KEY_NAME = "name";
    private String PREFS_NAME = "user_hotel_app_prefs";
    private SharedPreferences mUserPreference;
    private SharedPreferences.Editor editor;

    public UserPreference(Context mContext){
        this.mContext = mContext;
        mUserPreference = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = mUserPreference.edit();
    }

    public void setEmail(String email){
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public String getEmail(){
        return mUserPreference.getString(KEY_EMAIL, "");
    }

    public void setId(String id){
        editor.putString(KEY_ID, id);
        editor.commit();
    }

    public String getId(){
        return mUserPreference.getString(KEY_ID, "");
    }

    public void setName(String name){
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public String getName(){
        return mUserPreference.getString(KEY_NAME, "");
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }
}
