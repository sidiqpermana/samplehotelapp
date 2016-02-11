package com.sidiq.myhotelapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sidiq.myhotelapp.DetailHotelActivity;
import com.sidiq.myhotelapp.R;
import com.sidiq.myhotelapp.adapter.HotelAdapter;
import com.sidiq.myhotelapp.model.HotelItem;

import java.util.ArrayList;

public class CurrentLocationFragment extends Fragment {
    private ListView lvItem;

    private String[][] hotelItems = new String[][]{
            {"The Sunset Hotel & Restaurant", "Dewi Sri Street, Kuta, Bali, Indonesia", "3",
            "Hotel Sunset Bali terletak di lokasi yang sangat strategis dan jauh dari kemacetan. Hotel di Bali ini hanya 15 menit dari Bandara Internasional Ngurah Rai dan 10 menit ke Kuta pusat perbelanjaan. Perjalanan dan pengaturan wisata di Bali dapat dipesan di Receptionist.",
            "-8.344828", "115.675270", "http://cdn01.tiket.photos/img/business/t/h/business-the-sunset-hotel-amp-restaurant-hotel--5417.picture415x295.jpg"},
            {"The One Legian", "Jl. Legian No. 117, Badung, Bali, Indonesia", "4",
            "Voted as a Indonesia Leading Lifestyle Hotel by Indonesia Travel Tourism Awards 2014 and Bali Leading Lifestyle Hotel by Bali Tourism Awards 2015, The ONE Legian is committed to be the center of BALI, LIFESTYLE and BEYOND which offers ONE STOP Kuta-Legian Experience, a self Contain Hotel in the milieu of Kuta.",
            "-8.714009", "115.173590", "http://cdn01.tiket.photos/img/business/t/h/business-theonelegian-hotel-6100.picture415x295.jpg"},
            {"Serela Kuta Bali", "Jl. Raya Kuta 42XX, Bali, Indonesia", "3",
            "Serela Kuta Bali terletak strategis di jantung wilayah Kuta yang menjadi tempat terfavorit dan terkenal bagi para wisatawan. Anda dapat mengakses tempat populer di Kuta dan pantai Kuta dengan mudah ketika tinggal di hotel ini.",
            "-8.709454", "115.180984", "http://cdn01.tiket.photos/img/business/s/e/business-serela-kuta-bali-hotel--8768.picture415x295.jpg"}
    };
    private ArrayList<HotelItem> listHotelItem;

    public CurrentLocationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_location, container, false);
        lvItem = (ListView)view.findViewById(R.id.lv_item);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listHotelItem = new ArrayList<>();
        HotelItem hotelItem = null;
        for (int i = 0; i < hotelItems.length; i++){
            hotelItem = new HotelItem();
            hotelItem.setName(hotelItems[i][0]);
            hotelItem.setAddress(hotelItems[i][1]);
            hotelItem.setStar(hotelItems[i][2]);
            hotelItem.setDescription(hotelItems[i][3]);
            hotelItem.setLatitude(hotelItems[i][4]);
            hotelItem.setLongitude(hotelItems[i][5]);
            hotelItem.setImage(hotelItems[i][6]);
            listHotelItem.add(hotelItem);
        }
        HotelAdapter adapter = new HotelAdapter(getActivity(), listHotelItem);
        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailHotelActivity.toDetailHotelActivity(getActivity(), listHotelItem.get(position));
            }
        });
    }
}
