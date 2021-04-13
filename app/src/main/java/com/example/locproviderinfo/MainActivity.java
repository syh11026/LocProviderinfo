package com.example.locproviderinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView mTxtInfo;
    LocationManager locManager;
    List<String> locProviderList;
    //String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtInfo = findViewById(R.id.txtInfo);

        locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
       locProviderList = locManager.getAllProviders();
        mTxtInfo.setText("");

       for(String locProvider : locProviderList){
           mTxtInfo.append("Loc. Provider : " + locProvider +"\n");
           mTxtInfo.append("Enabled : " + locManager.isProviderEnabled(locProvider)+"\n\n"); //status
       }

    }
}