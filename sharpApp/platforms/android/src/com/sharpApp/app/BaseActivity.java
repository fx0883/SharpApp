package com.sharpApp.app;

import android.R.integer;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import org.apache.cordova.*;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class BaseActivity extends CordovaActivity{
	private InterstitialAd mInterstitial;
	private Boolean isFirstInterstitial = true;
	
	private int times=0;
	private int canShowTimes=10;
	private String startUpTime="startUpTime";
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
				    saveAdTime();
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

        //��������
        mInterstitial.loadAd(new AdRequest.Builder().build());
    }
    
    public void showInterstitial()
    {
        if (mInterstitial.isLoaded()&&bIsCanShowAd()) {
            mInterstitial.show();
            mInterstitial.loadAd(new AdRequest.Builder().build());
        }	
        
    }
    
    @Override
    protected void onResume()
    {
    	super.onResume();
    	showInterstitial();    	
    }
    
    @Override
    public void onDestroy() {
        
        super.onDestroy();

        saveAdTime();
    }
    
    public int getAdTime()
    {
    	SharedPreferences adSettings = getSharedPreferences("AdSetting", 0);
    	times = adSettings.getInt(startUpTime, 0);
		return times;
    	
    }
   
    public Boolean bIsCanShowAd()
    {
    	Boolean flag = false;
    	if (times==0) {
			getAdTime();
		};
    	flag = times>canShowTimes;
    	//Log.d(tag, msg)(times); 
    	
    	Log.w(startUpTime,times+"");
    	return flag;
    }
     
		
	public void saveAdTime()
	{
		SharedPreferences adSettings = getSharedPreferences("AdSetting", 0);
		SharedPreferences.Editor editor = adSettings.edit();
		editor.putInt(startUpTime, ++times);
		editor.commit();
		
	}
	
}
