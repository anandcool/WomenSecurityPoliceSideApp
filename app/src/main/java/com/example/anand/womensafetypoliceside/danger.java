package com.example.anand.womensafetypoliceside;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.anand.womensafetypoliceside.Mydata.recdangerlocation;

public class danger extends AppCompatActivity {

    ArrayList<String> latitude = new ArrayList<String>();
    ArrayList<String> longtitude = new ArrayList<String>();
    ArrayList<String> name = new ArrayList<String>();
    ListView mlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger);
        String str = recdangerlocation();
        String[] str2 = str.split("&");
        for (int i = 0; i < str2.length; i++) {

            if (i == 0) { // 0 stands for latitude from database
                String[] str3 = str2[i].split(",");
                //Toast.makeText(this, ""+str3.length, Toast.LENGTH_SHORT).show();
                for (int j = 0; j < str3.length; j++) {
                    latitude.add(str3[j]);
//                    Toast.makeText(this, ""+latitude.size(), Toast.LENGTH_SHORT).show();
                }
            }
            if(i==1){ // 0 stands for latitude from database
                String[] str3= str2[i].split(",");
                //Toast.makeText(this, ""+str3.length, Toast.LENGTH_SHORT).show();
                for(int j=0;j<str3.length;j++){
                    longtitude.add(str3[j]);
//                    Toast.makeText(this, ""+longtitude.size(), Toast.LENGTH_SHORT).show();
                }
            }
            if(i==1){ // 0 stands for latitude from database
                String[] str3= str2[i].split(",");
                //Toast.makeText(this, ""+str3.length, Toast.LENGTH_SHORT).show();
                for(int j=0;j<str3.length;j++){
                    name.add(str3[j]);
//                    Toast.makeText(this, ""+name.size(), Toast.LENGTH_SHORT).show();
                }
            }
        }
       // Toast.makeText(this, "bbb"+str, Toast.LENGTH_SHORT).show();
        mlistview = findViewById(R.id.list);
        CustomAdapter customAdapter = new CustomAdapter();
        mlistview.setAdapter(customAdapter);
            }

            class CustomAdapter extends BaseAdapter {

                @Override
                public int getCount() {
                    return latitude.size();
                }

                @Override
                public Object getItem(int i) {
                    return null;
                }

                @Override
                public long getItemId(int i) {
                    return 0;
                }

                @Override
                public View getView(final int i, View view, ViewGroup viewGroup) {
                    view = getLayoutInflater().inflate(R.layout.customlayout, null);
//            ImageView iv = view.findViewById(R.id.imageView);
                    TextView mTextView = view.findViewById(R.id.textview);
                    TextView mTextView1 = view.findViewById(R.id.textview1);
                    mTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                         //   Toast.makeText(danger.this, "Latitude" + latitude.get(i), Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(getApplicationContext(), MapsActivity.class);
                            in.putExtra("Latitude", "" + latitude.get(i));
                            in.putExtra("Longtitude", "" + longtitude.get(i));
                            in.putExtra("email", "" + name.get(i));
                            startActivity(in);
                        }
                    });
                    mTextView.setText("Current Location-:" + latitude.get(i) + " " + longtitude.get(i));
                    mTextView1.setText("email-:" + name.get(i));
                    return view;
                }
            }
        }
