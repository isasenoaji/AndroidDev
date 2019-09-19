package com.example.googlemapsapp;

import androidx.fragment.app.FragmentActivity;

import android.hardware.camera2.TotalCaptureResult;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button go =(Button) findViewById(R.id.goButton);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.goButton:sembunyikanKeyboar(view);
                        GoToLokasi();
                        break;
                }
            }
        });

        Button cari = (Button) findViewById(R.id.searchButton);
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.searchButton : goCari();
                    break;
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in ITS and move the camera
        LatLng ITS = new LatLng(-7.28, 112.79);

        mMap.addMarker(new MarkerOptions().position(ITS).title("Marker in ITS"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ITS,15));
    }

    private void GoToPeta(Double lat, Double Lg, float x){
        LatLng LokasiBaru = new LatLng(lat,Lg);

        mMap.addMarker(new MarkerOptions().position(LokasiBaru).title("New Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LokasiBaru,x));
    }

    private void goCari(){
        EditText tempat = (EditText) findViewById(R.id.SearchVal);
        EditText zoom = (EditText) findViewById(R.id.ZoomVal);
        Geocoder g = new Geocoder(getBaseContext());
        try {
            List<android.location.Address> daftar = g.getFromLocationName(tempat.getText().toString(),1);
            Address alamat = daftar.get(0);

            String nemuAlamat = alamat.getAddressLine(0);
            Double lintang = alamat.getLatitude();
            Double bujur = alamat.getLongitude();
            Float Zom = Float.parseFloat(zoom.getText().toString());

            Toast.makeText(this,"Ketemu "+nemuAlamat, Toast.LENGTH_LONG).show();
            GoToPeta(lintang,bujur,Zom);

            EditText lat = (EditText) findViewById(R.id.LatVal);
            EditText lang = (EditText) findViewById(R.id.LangVal);

            lat.setText(lintang.toString());
            lang.setText(bujur.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void GoToLokasi(){
        EditText lat = (EditText) findViewById(R.id.LatVal);
        EditText lang = (EditText) findViewById(R.id.LangVal);
        EditText Zoom = (EditText) findViewById(R.id.ZoomVal);

        Double dbLat = Double.parseDouble(lat.getText().toString());
        Double dbLang = Double.parseDouble(lang.getText().toString());
        Float zoom = Float.parseFloat(Zoom.getText().toString());

        Toast.makeText(this,"Move to lat:"+dbLat+"Long :"+ dbLang,Toast.LENGTH_LONG).show();
        GoToPeta(dbLat,dbLang,zoom);
    }

    private void sembunyikanKeyboar(View view){
        InputMethodManager a = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        a.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}
