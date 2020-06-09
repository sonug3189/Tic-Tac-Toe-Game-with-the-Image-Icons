package com.Tic_tac_toe_for_kids.pro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appnext.ads.fullscreen.FullScreenVideo;
import com.appnext.ads.fullscreen.FullscreenConfig;
import com.appnext.ads.fullscreen.Video;
import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerSize;
import com.appnext.banners.BannerView;
import com.appnext.base.Appnext;
import com.appnext.core.Ad;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Tictac extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
//private FullScreenVideo fullscreen;


    private ImageView m_OPlayerLogoImage;
    private ImageView m_XPlayerLogoImage;
    private TextView m_WinTextView;

    private int m_CurrentPlayerTurn = 0;    // 0 for circle and 1 for cross
    private boolean m_IsGameOver = false;   // is game over

    private int[] m_PlayerLogoColor = {
            R.color.player_o_color,
            R.color.player_x_color
    };

    private int[] m_PlayerLogo = {
            R.drawable.circle,
            R.drawable.cross
    };

    private int[] m_GameGrids = {
            2, 2, 2,
            2, 2, 2,
            2, 2, 2
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

//        fullscreen = new FullScreenVideo(this, "a48ac80e-7c52-402d-b061-b7d6ca400823");
//        fullscreen.loadAd();
//        fullscreen.setBackButtonCanClose(true);
//        fullscreen.setShowClose(true);

//        BannerView banner = new BannerView(this);
//        banner.setPlacementId("a48ac80e-7c52-402d-b061-b7d6ca400823");
//        banner.setBannerSize(BannerSize.BANNER);
//
//        BannerView bannerView = (BannerView) findViewById(R.id.banner);
//        bannerView.loadAd(new BannerAdRequest());

//        MobileAds.initialize(this,
//                "ca-app-pub-3940256099942544~3347511713");


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        m_CurrentPlayerTurn = 0;

        m_OPlayerLogoImage = findViewById(R.id.oPlayerLogo);
        m_XPlayerLogoImage = findViewById(R.id.xPlayerLogo);
        setActivePlayerColor();

        m_WinTextView = findViewById(R.id.win_text);
        m_WinTextView.setText("");
    }

    private void setActivePlayerColor() {
        if (m_CurrentPlayerTurn == 0) {

            m_OPlayerLogoImage.setBackground(getResources().getDrawable(R.drawable.active_grid_shape));
            m_XPlayerLogoImage.setBackground(getResources().getDrawable(R.drawable.grid_shape));
        }   else {
            m_XPlayerLogoImage.setBackground(getResources().getDrawable(R.drawable.active_grid_shape));
            m_OPlayerLogoImage.setBackground(getResources().getDrawable(R.drawable.grid_shape));
        }
    }

    private void checkGameOver() {
        // check in rows
        if (m_GameGrids[0] == m_CurrentPlayerTurn && m_GameGrids[1] == m_CurrentPlayerTurn && m_GameGrids[2] == m_CurrentPlayerTurn)
            m_IsGameOver = true;

        if (m_GameGrids[3] == m_CurrentPlayerTurn && m_GameGrids[4] == m_CurrentPlayerTurn && m_GameGrids[5] == m_CurrentPlayerTurn)
            m_IsGameOver = true;

        if (m_GameGrids[6] == m_CurrentPlayerTurn && m_GameGrids[7] == m_CurrentPlayerTurn && m_GameGrids[8] == m_CurrentPlayerTurn)
            m_IsGameOver = true;

        // check in column
        if (m_GameGrids[0] == m_CurrentPlayerTurn && m_GameGrids[3] == m_CurrentPlayerTurn && m_GameGrids[6] == m_CurrentPlayerTurn)
            m_IsGameOver = true;

        if (m_GameGrids[1] == m_CurrentPlayerTurn && m_GameGrids[4] == m_CurrentPlayerTurn && m_GameGrids[7] == m_CurrentPlayerTurn)
            m_IsGameOver = true;

        if (m_GameGrids[2] == m_CurrentPlayerTurn && m_GameGrids[5] == m_CurrentPlayerTurn && m_GameGrids[8] == m_CurrentPlayerTurn)
            m_IsGameOver = true;

        // check in diagonal
        if (m_GameGrids[0] == m_CurrentPlayerTurn && m_GameGrids[4] == m_CurrentPlayerTurn && m_GameGrids[8] == m_CurrentPlayerTurn)
            m_IsGameOver = true;

        if (m_GameGrids[2] == m_CurrentPlayerTurn && m_GameGrids[4] == m_CurrentPlayerTurn && m_GameGrids[6] == m_CurrentPlayerTurn)
            m_IsGameOver = true;

    }

    private boolean isGameDraw()
    {
        for (int i = 0; i < m_GameGrids.length; i++){
            if (m_GameGrids[i] == 2)
                return false;
        }

        return true;
    }

    public void onGridTapped(View view) {
        if (m_IsGameOver) {
            Toast.makeText(this, "game over!, please restart game", Toast.LENGTH_SHORT).show();
            return;
        }

        ImageView gridImage = (ImageView) view;
        int index = Integer.parseInt(gridImage.getTag().toString());

        if (m_GameGrids[index] == 2) {
            m_GameGrids[index] = m_CurrentPlayerTurn;
            gridImage.setImageResource(m_PlayerLogo[m_CurrentPlayerTurn]);  // set player logo

            checkGameOver();
            if (isGameDraw()){
                m_WinTextView.setTextColor(getResources().getColor(R.color.active_player));
                m_WinTextView.setText("It's Draw!");
                m_IsGameOver = true;
            } else if (!m_IsGameOver) {
                m_CurrentPlayerTurn = (m_CurrentPlayerTurn == 0) ? 1 : 0;        // change player turn
                setActivePlayerColor();
            } else {
                m_WinTextView.setTextColor(getResources().getColor(m_PlayerLogoColor[m_CurrentPlayerTurn]));
                String logo = (m_CurrentPlayerTurn == 0) ? "sukha" : "kahlon";
                m_WinTextView.setText("Player " + logo + " win!");
            }
        } else {
            Toast.makeText(this, "this place is already filled", Toast.LENGTH_SHORT).show();
        }
    }

    public void restartGame(View view) {

//        fullscreen.showAd();
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }else {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
        m_IsGameOver = false;
        m_WinTextView.setText("");

        for (int i = 0; i < m_GameGrids.length; i++)
            m_GameGrids[i] = 2;

        LinearLayout linearLayout = findViewById(R.id.row1);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            ImageView gridImage = (ImageView) linearLayout.getChildAt(i);
            gridImage.setImageResource(0);
        }

        linearLayout = findViewById(R.id.row2);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            ImageView gridImage = (ImageView) linearLayout.getChildAt(i);
            gridImage.setImageResource(0);
        }

        linearLayout = findViewById(R.id.row3);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            ImageView gridImage = (ImageView) linearLayout.getChildAt(i);
            gridImage.setImageResource(0);
        }
    }
}
