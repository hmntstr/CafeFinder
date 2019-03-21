package com.cafe.cafefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class RecyclerViewActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{
    MyRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.places);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < GetNearbyPlacesData.ratings.size(); i++) {
            double a = Double.parseDouble(GetNearbyPlacesData.ratings.get(i));
            for (int j = 0; j < GetNearbyPlacesData.ratings.size(); j++) {
                double b = Double.parseDouble(GetNearbyPlacesData.ratings.get(j));
                if (a > b) {
                    String temp1 = GetNearbyPlacesData.ratings.get(i);
                    String temp2 = GetNearbyPlacesData.placeNames.get(i);
                    GetNearbyPlacesData.ratings.set(i, GetNearbyPlacesData.ratings.get(j));
                    GetNearbyPlacesData.placeNames.set(i, GetNearbyPlacesData.placeNames.get(j));
                    GetNearbyPlacesData.ratings.set(j, temp1);
                    GetNearbyPlacesData.placeNames.set(j, temp2);
                }
            }
        }
        adapter = new MyRecyclerViewAdapter(this, GetNearbyPlacesData.placeNames, GetNearbyPlacesData.ratings);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
