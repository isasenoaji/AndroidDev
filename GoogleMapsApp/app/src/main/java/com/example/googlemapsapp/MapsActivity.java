package com.example.googlemapsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.TotalCaptureResult;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager lm;
    private LocationListener ll;

    public static final int PERMISSION_GET_LAST_LOCATION = 10;
    public static final int PERMISSION_REQUEST_LOCATION_UPDATES = 11;
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS/2;
    protected final static String REQUESTING_LOCATION_UPDATES_KEY = "requesting-location-updates-key";
    protected final static String LOCATION_KEY = "location-key";
    protected final static String LAST_UPDATED_TIME_STRING_KEY = "last-updated-string-key";

    protected String mLatLabel = "Lat : ";
    protected String mLongLabel = "long : ";
    protected String mLastUpdateTime = "";
    protected String mLastUpdateLabel = "Last Update :";
    protected EditText latVal;
    protected EditText longVal;
    protected EditText updateTime;
    protected Button startUpdate;
    protected Button stopUpdate;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        latVal = (EditText) findViewById(R.id.LatVal);
        longVal = (EditText) findViewById(R.id.LangVal);
        updateTime = (EditText) findViewById(R.id.Update);
        startUpdate = (Button) findViewById(R.id.startButton);
        stopUpdate = (Button) findViewById(R.id.stopButton);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ll = new lokasiListener();


        startUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startUpdate.setEnabled(false);
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 50, ll);
            }
        });

        stopUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startUpdate.setEnabled(true);
                mMap.clear();
            }
        });



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    //from part one
    private class lokasiListener implements LocationListener{
        private EditText txtLat,txtLong;
        @Override
        public void onLocationChanged(Location location) {
            mLastUpdateTime = DateFormat.getDateTimeInstance().format(new Date());

            String[] fix = mLastUpdateTime.split(" ");
            mLastUpdateTime = fix[3].replace(".",":");
            latVal.setText(String.valueOf(location.getLatitude()));
            longVal.setText(String.valueOf(location.getLongitude()));
            updateTime.setText(String.format("%s",mLastUpdateTime));

            Double Lat = Double.parseDouble(String.valueOf(location.getLatitude()));
            Double Long = Double.parseDouble(String.valueOf(location.getLongitude()));

            GoToPeta(Lat,Long,15);
            Toast.makeText(getBaseContext(),"GPS Capture",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
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

//    private void goCari(){
//        EditText tempat = (EditText) findViewById(R.id.SearchVal);
//        EditText zoom = (EditText) findViewById(R.id.ZoomVal);
//        Geocoder g = new Geocoder(getBaseContext());
//        try {
//            List<android.location.Address> daftar = g.getFromLocationName(tempat.getText().toString(),1);
//            Address alamat = daftar.get(0);
//
//            String nemuAlamat = alamat.getAddressLine(0);
//            Double lintang = alamat.getLatitude();
//            Double bujur = alamat.getLongitude();
//            Float Zom = Float.parseFloat(zoom.getText().toString());
//
//            Toast.makeText(this,"Ketemu "+nemuAlamat, Toast.LENGTH_LONG).show();
//            GoToPeta(lintang,bujur,Zom);
//
//            EditText lat = (EditText) findViewById(R.id.LatVal);
//            EditText lang = (EditText) findViewById(R.id.LangVal);
//
//            lat.setText(lintang.toString());
//            lang.setText(bujur.toString());
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    private void GoToLokasi(){
        EditText lat = (EditText) findViewById(R.id.LatVal);
        EditText lang = (EditText) findViewById(R.id.LangVal);
//        EditText Zoom = (EditText) findViewById(R.id.ZoomVal);

        Double dbLat = Double.parseDouble(lat.getText().toString());
        Double dbLang = Double.parseDouble(lang.getText().toString());
//        Float zoom = Float.parseFloat(Zoom.getText().toString());

        Toast.makeText(this,"Move to lat:"+dbLat+"Long :"+ dbLang,Toast.LENGTH_LONG).show();
//        GoToPeta(dbLat,dbLang,zoom);
    }

    private void sembunyikanKeyboar(View view){
        InputMethodManager a = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        a.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}
