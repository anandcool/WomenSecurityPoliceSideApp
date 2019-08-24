package com.example.anand.womensafetypoliceside;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnotify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            btnotify = findViewById(R.id.notify);
            btnotify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //String str = Mydata.recdangerlocation();
                   // Toast.makeText(this, ""+str, Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(MainActivity.this,danger.class);
                    startActivity(in);
                }
            });

    }
}
