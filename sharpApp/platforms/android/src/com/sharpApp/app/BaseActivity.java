package com.sharpApp.app;

import android.app.Activity;
import android.os.Bundle;

import org.apache.cordova.*;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class BaseActivity extends CordovaActivity{
	private InterstitialAd mInterstitial;
	private Boolean isFirstInterstitial = true;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInterstitial = new InterstitialAd(this);
//        mInterstitial.setAdUnitId(getResources().getString(R.string.ad_unit_id));
        mInterstitial.setAdUnitId("ca-app-pub-5934917937656240/7917750418");
        // Set an AdListener.
        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            	if (isFirstInterstitial) {
					isFirstInterstitial = false;
					showInterstitial();
					
				}
            }

            @Override
            public void onAdClosed() {
            	 mInterstitial.loadAd(new AdRequest.Builder().build());
            }
            
            @Override
            public void onAdFailedToLoad(int errorCode) 
            {
            	
            	mInterstitial.loadAd(new AdRequest.Builder().build());
            };
        });

        //º”‘ÿ ˝æ›
        mInterstitial.loadAd(new AdRequest.Builder().build());
    }
    
    public void showInterstitial()
    {
        if (mInterstitial.isLoaded()) {
            mInterstitial.show();
        }	
        mInterstitial.loadAd(new AdRequest.Builder().build());
    }
	
}
