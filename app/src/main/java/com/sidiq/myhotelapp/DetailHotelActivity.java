package com.sidiq.myhotelapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sidiq.myhotelapp.model.HotelItem;
import com.sidiq.myhotelapp.model.Weather;

import cz.msebera.android.httpclient.Header;

public class DetailHotelActivity extends AppCompatActivity implements View.OnClickListener{

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    public static String KEY_HOTEL = "hotelItem";
    private HotelItem mHotelItem;

    private TextView txtName, txtPrice, txtAddress, txtDescription, txtWeather;
    private ImageView imgDetail, imgMore, imgWeather;
    private ImageView imgGalleryA, imgGalleryB, imgGalleryC;
    private ImageView[] gallery = null;

    private LinearLayout lnComments, lnReviews;


    public static String imageUrls[] = new String[]{
            "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--3677.picture415x295.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--5423.picture415x295.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--1327.picture415x295.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--5417.picture415x295.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--6467.picture415x295.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--3390.picture415x295.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--7755.picture415x295.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--7699.picture415x295.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--6913.picture415x295.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--6059.picture415x295.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--2491.picture415x295.jpg",
            "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--7864.picture415x295.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);

        mHotelItem = (HotelItem)getIntent().getSerializableExtra(KEY_HOTEL);

        getSupportActionBar().setTitle("Detail Hotel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtName = (TextView)findViewById(R.id.txt_detail_name);
        txtAddress = (TextView)findViewById(R.id.txt_detail_location);
        txtDescription = (TextView)findViewById(R.id.txt_detail_description);
        txtPrice = (TextView)findViewById(R.id.txt_detail_price);
        txtWeather = (TextView)findViewById(R.id.txt_detail_weather);
        imgDetail = (ImageView)findViewById(R.id.img_detail_photo);
        imgGalleryA = (ImageView)findViewById(R.id.img_item_a);
        imgGalleryB = (ImageView)findViewById(R.id.img_item_b);
        imgGalleryC = (ImageView)findViewById(R.id.img_item_c);
        imgMore = (ImageView)findViewById(R.id.img_more);
        imgWeather = (ImageView)findViewById(R.id.img_weather_icon);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        lnComments = (LinearLayout)findViewById(R.id.ln_comments);
        lnReviews = (LinearLayout)findViewById(R.id.ln_reviews);

        imgMore.setOnClickListener(this);
        imgGalleryA.setOnClickListener(this);
        imgGalleryB.setOnClickListener(this);
        imgGalleryC.setOnClickListener(this);
        lnReviews.setOnClickListener(this);
        lnComments.setOnClickListener(this);

        gallery = new ImageView[3];

        txtName.setText(mHotelItem.getName());
        txtDescription.setText(mHotelItem.getDescription());
        txtAddress.setText(mHotelItem.getAddress());
        txtPrice.setText("Mulai dari Rp. 300K");
        Glide.with(DetailHotelActivity.this).load(mHotelItem.getImage()).into(imgDetail);

        gallery[0] = imgGalleryA;
        gallery[1] = imgGalleryB;
        gallery[2] = imgGalleryC;

        for (int i = 0; i < 3; i++){
            Glide.with(DetailHotelActivity.this).load(imageUrls[i]).into(gallery[i]);
        }

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(Double.parseDouble(mHotelItem.getLatitude()),
                        Double.parseDouble(mHotelItem.getLongitude()));
                mMap.addMarker(new MarkerOptions().position(sydney).title(mHotelItem.getName()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            }
        });

        getCurrentWeather();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hotel, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }

        if (item.getItemId() == R.id.action_share){
            shareContent();
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareContent(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                mHotelItem.getName()+"\n"+mHotelItem.getDescription());
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    public static void toDetailHotelActivity(Activity activity, HotelItem mHotelItem){
        Intent mIntent = new Intent(activity, DetailHotelActivity.class);
        mIntent.putExtra(KEY_HOTEL, mHotelItem);
        activity.startActivityForResult(mIntent, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_more:
                GalleryActivity.toGalleryActivity(DetailHotelActivity.this);
                break;

            case R.id.img_item_a:
                ImageDetailActivity.toImageDetailActivity(DetailHotelActivity.this,
                        imageUrls[0]);
                break;

            case R.id.img_item_b:
                ImageDetailActivity.toImageDetailActivity(DetailHotelActivity.this,
                        imageUrls[1]);
                break;

            case R.id.img_item_c:
                ImageDetailActivity.toImageDetailActivity(DetailHotelActivity.this,
                        imageUrls[2]);
                break;

            case R.id.ln_comments:
                CommentActivity.toCommentActivity(DetailHotelActivity.this);
                break;
        }
    }

    private void getCurrentWeather(){
        txtWeather.setText("Mendeteksi cuaca saat ini...");
        AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();
        String url = "http://api.openweathermap.org/data/2.5/weather?" +
                "lat="+mHotelItem.getLatitude()+
                "&lon="+mHotelItem.getLongitude()+"&appid=44db6a862fba0b067b1930da0d769e98";
        mAsyncHttpClient.get(DetailHotelActivity.this, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Gson gson = new Gson();
                Weather mWeather = gson.fromJson(response, Weather.class);
                if (mWeather.code == 200){
                    double celcius = mWeather.mWeatherMain.temp - 273;

                    String icon = mWeather.listWeather.get(0).icon;
                    String urlIcon = "http://openweathermap.org/img/w/"+icon+".png";
                    String weatherName = mWeather.listWeather.get(0).weatherName;

                    txtWeather.setText(weatherName+" "+Math.round(celcius)+" Derajat");
                    Glide.with(DetailHotelActivity.this).load(urlIcon).into(imgWeather);
                }else{
                    txtWeather.setText("Gagal mendeteksi cuaca saat ini");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                txtWeather.setText("Gagal mendeteksi cuaca saat ini");
            }
        });


    }
}
