package com.sidiq.myhotelapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sidiq.myhotelapp.api.ApiUrl;
import com.sidiq.myhotelapp.model.Login;
import com.sidiq.myhotelapp.preference.UserPreference;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnLogin;
    private EditText edtEmail, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        btnLogin = (Button)findViewById(R.id.btn_login);
        edtEmail = (EditText)findViewById(R.id.edt_username);
        edtPassword = (EditText)findViewById(R.id.edt_password);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login){
            String username = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
                Toast.makeText(LoginActivity.this,
                        "Field tidak boleh kosong",
                        Toast.LENGTH_LONG).show();
            }else{
                loginAsync(username, password);
            }
        }
    }

    private void loginAsync(String username, String password){
        String url = ApiUrl.URL_LOGIN;
        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("password", password);

        final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        dialog.setTitle("Login");
        dialog.setMessage("Harap tunggu...");
        dialog.show();

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(LoginActivity.this, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                dialog.dismiss();

                String response = new String(responseBody);
                Gson gson = new Gson();
                Login mLogin = gson.fromJson(response, Login.class);
                if (mLogin.success == 1){
                    UserPreference mUserPreference = new UserPreference(LoginActivity.this);
                    mUserPreference.setEmail(mLogin.mUser.email);
                    mUserPreference.setId(mLogin.mUser.idUser);
                    mUserPreference.setName(mLogin.mUser.nama);

                    Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mIntent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,
                            mLogin.message,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this,
                        "Tidak dapat terhubung ke server. Silakan coba kembali",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
