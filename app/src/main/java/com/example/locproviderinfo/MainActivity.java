package com.example.locproviderinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView mTxtInfo;
    LocationManager locManager;
    List<String> locProviderList;
    //String s;
    LocationProvider locProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtInfo = findViewById(R.id.txtInfo);

        locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
       locProviderList = locManager.getAllProviders();
        mTxtInfo.setText("");

       for(String locProviderName : locProviderList){
           mTxtInfo.append("Loc. Provider : " + locProviderName +"\n");
           mTxtInfo.append("Enabled : " + locManager.isProviderEnabled(locProviderName)+"\n\n"); //status
           switch (locProviderName){
               case "gps":
                    locProvider = locManager.getProvider(LocationManager.GPS_PROVIDER);
                       break;
               case "network":
                   locProvider = locManager.getProvider(LocationManager.NETWORK_PROVIDER);
                   break;
               case "passive" :
                   locProvider = locManager.getProvider(LocationManager.PASSIVE_PROVIDER);
                   break;
           }
           printInfo(locProvider);

       }

    }
    private void printInfo(LocationProvider locationProvider) {
        mTxtInfo.append("이름: " + locationProvider.getName() + "\n");
        mTxtInfo.append("현재이용가능여부: " + locManager.isProviderEnabled(locationProvider.getName()) + "\n");
        mTxtInfo.append("위성필요여부: " + locationProvider.requiresSatellite() + "\n");
        mTxtInfo.append("인터넷접속필요여부: " + locationProvider.requiresNetwork() + "\n");
        mTxtInfo.append("기지국필요여부: " + locationProvider.requiresCell() + "\n");

        mTxtInfo.append("고도정보지원여부: " + locationProvider.supportsAltitude() + "\n");
        mTxtInfo.append("방향정보지원여부: " + locationProvider.supportsBearing() + "\n");
        mTxtInfo.append("속도정보지원여부: " + locationProvider.supportsSpeed() + "\n");


        String accuracy = "";
        switch(locationProvider.getAccuracy()) {
            //2.3부터 사용
            case Criteria.ACCURACY_LOW:    accuracy="500m 이상 오차"; break;
            case Criteria.ACCURACY_MEDIUM: accuracy="100~500m 오차"; break;
            case Criteria.ACCURACY_HIGH:   accuracy="0~100m 오차"; break;
        }
        mTxtInfo.append("정확성정도: " + accuracy + "\n");

        String power = "";
        switch(locationProvider.getPowerRequirement()) {
            case Criteria.POWER_LOW:    power="적게 사용"; break;
            case Criteria.POWER_MEDIUM: power="중간 사용"; break;
            case Criteria.POWER_HIGH:   power="많이 사용"; break;
        }
        mTxtInfo.append("전원사용정도: " + power + "\n");

        mTxtInfo.append("요금지불여부: " + locationProvider.hasMonetaryCost() + "\n");
        mTxtInfo.append("\n");
    }
}