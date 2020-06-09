package com.Tic_tac_toe_for_kids.pro;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerSize;
import com.appnext.banners.BannerView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class splashscreen extends AppCompatActivity {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

//        BannerView banner = new BannerView(this);
//        banner.setPlacementId("a48ac80e-7c52-402d-b061-b7d6ca400823");
//        banner.setBannerSize(BannerSize.BANNER);
//
//        BannerView bannerView = (BannerView) findViewById(R.id.banner);
//        bannerView.loadAd(new BannerAdRequest());


        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                startActivity(new Intent(getApplicationContext(), Tictac.class));
            }
        });

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(splashscreen.this,RulesActivity.class));
            }
        });


        Button button3 =(Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);

            }
        });



    }
}


