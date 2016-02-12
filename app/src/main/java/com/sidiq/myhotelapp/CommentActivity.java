package com.sidiq.myhotelapp;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sidiq.myhotelapp.adapter.CommentAdapter;
import com.sidiq.myhotelapp.api.ApiUrl;
import com.sidiq.myhotelapp.model.Comment;
import com.sidiq.myhotelapp.model.Login;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class CommentActivity extends AppCompatActivity {
    private ListView lvComment;
    private ProgressBar indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        lvComment = (ListView)findViewById(R.id.lv_comment);
        indicator = (ProgressBar)findViewById(R.id.pb_indicator);

        getSupportActionBar().setTitle("Komentar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getCommentAsync();
    }

    private void getCommentAsync(){
        String url = ApiUrl.URL_COMMENT + "?id_hotel=34";
        indicator.setVisibility(View.VISIBLE);
        lvComment.setVisibility(View.GONE);

        AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();
        mAsyncHttpClient.get(CommentActivity.this, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                indicator.setVisibility(View.GONE);
                lvComment.setVisibility(View.VISIBLE);

                String response = new String(responseBody);
                Log.d("Comment", response);

                Gson gson = new Gson();
                Comment comment = gson.fromJson(response, Comment.class);
                if (comment.listComment.size() > 0) {
                    CommentAdapter adapter = new CommentAdapter(CommentActivity.this,
                            comment.listComment);
                    lvComment.setAdapter(adapter);
                } else {
                    showErrorRetryBar("Tidak ada data", false);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                indicator.setVisibility(View.GONE);
                lvComment.setVisibility(View.GONE);
                showErrorRetryBar("Tidak terhubung", true);
            }
        });
    }

    private void showErrorRetryBar(String message, boolean needRetry) {
        Snackbar mSnackbar = Snackbar.make(lvComment, message, Snackbar.LENGTH_LONG);
        if (needRetry){
            mSnackbar.setAction("Reload", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getCommentAsync();
                }
            });
        }
        mSnackbar.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static void toCommentActivity(Activity mActivity){
        Intent mIntent = new Intent(mActivity, CommentActivity.class);
        mActivity.startActivity(mIntent);
    }
}
