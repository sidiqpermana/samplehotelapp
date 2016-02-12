package com.sidiq.myhotelapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sidiq.myhotelapp.preference.UserPreference;

public class SplashscreenActivity extends AppCompatActivity {

    private UserPreference mUserPreference;
    private DelayAsync delayAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        getSupportActionBar().hide();

        mUserPreference = new UserPreference(SplashscreenActivity.this);

        delayAsync = new DelayAsync();
        delayAsync.execute();
    }

    class DelayAsync extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                Thread.sleep(3000);
            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Intent mIntent = null;
            if (mUserPreference.getEmail().equals("")){
                mIntent = new Intent(SplashscreenActivity.this, LoginActivity.class);
            }else{
                mIntent = new Intent(SplashscreenActivity.this, MainActivity.class);
            }
            startActivity(mIntent);
            finish();
        }



    }
}
