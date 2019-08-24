package com.example.anand.womensafetypoliceside;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;
//import com.google.android.gms.location.LocationListener

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    private GoogleMap mMap;
    String latitude,longtitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Bundle b1 = getIntent().getExtras();
        latitude= b1.getString("Latitude");
        longtitude = b1.getString("Longtitude");
        String update = Mydata.updatestatus(latitude,longtitude);
//        Toast.makeText(this, ""+update, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, ""+latitude+","+longtitude, Toast.LENGTH_SHORT).show();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Sorry GPS is Off", Toast.LENGTH_SHORT).show();
        } else {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5, 0, (LocationListener) this);
            Toast.makeText(this, "Status Updating", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Geocoder gc = new Geocoder(MapsActivity.this);
        try {
            ArrayList<Address> al = (ArrayList) gc.getFromLocation(Double.parseDouble(latitude),Double.parseDouble(longtitude), 1);
            android.location.Address ad = al.get(0);
            //Toast.makeText(this, ""+ad, Toast.LENGTH_SHORT).show();
          String  str = ad.getSubLocality()+","+ad.getLocality();
             //  Toast.makeText(this, ""+str, Toast.LENGTH_SHORT).show();
            LatLng sydney = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longtitude));
                mMap.addMarker(new MarkerOptions().position(sydney).title(""+str));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 20));
    }
    catch (Exception ex){
        Toast.makeText(this, ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

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
