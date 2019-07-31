package com.ls.dareman

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd


class DashFragment: DMFragment() {
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val v = inflater.inflate(com.ls.dareman.R.layout.fragment_menu, container, false)
        initInterstitialAds()

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val buttonOne = activity!!.findViewById(com.ls.dareman.R.id.menu_one) as Button
        buttonOne.setTextSize(TypedValue.COMPLEX_UNIT_PX, 48F)
        buttonOne.setOnClickListener {
//            Toast.makeText(activity, "hoge!", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, SplashActivity::class.java)
            startActivity(intent)

            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            }
        }

        val buttonTwo = activity!!.findViewById(com.ls.dareman.R.id.menu_two) as Button
        buttonTwo.setTextSize(TypedValue.COMPLEX_UNIT_PX, 48F)
        buttonTwo.setOnClickListener {
            Toast.makeText(activity, "只今コンテンツを準備中です。", Toast.LENGTH_SHORT).show()

            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            }
        }

//        val buttonThree = activity!!.findViewById(com.ls.dareman.R.id.menu_three) as Button
//        buttonThree.setTextSize(TypedValue.COMPLEX_UNIT_PX, 48F)
//        buttonThree.setOnClickListener {
//            Toast.makeText(activity, "Contents preparing...", Toast.LENGTH_SHORT).show()
//
//            if (mInterstitialAd.isLoaded) {
//                mInterstitialAd.show()
//            }
//        }
//
//        val buttonFour = activity!!.findViewById(com.ls.dareman.R.id.menu_four) as Button
//        buttonFour.setTextSize(TypedValue.COMPLEX_UNIT_PX, 48F)
//        buttonFour.setOnClickListener {
//            Toast.makeText(activity, "Contents preparing...", Toast.LENGTH_SHORT).show()
//
//            if (mInterstitialAd.isLoaded) {
//                mInterstitialAd.show()
//            }
//        }
//
//        val buttonFive = activity!!.findViewById(com.ls.dareman.R.id.menu_five) as Button
//        buttonFive.setTextSize(TypedValue.COMPLEX_UNIT_PX, 48F)
//        buttonFive.setOnClickListener {
//            Toast.makeText(activity, "Contents preparing...", Toast.LENGTH_SHORT).show()
//
//            if (mInterstitialAd.isLoaded) {
//                mInterstitialAd.show()
//            }
//        }
//
//        val buttonSix = activity!!.findViewById(com.ls.dareman.R.id.menu_six) as Button
//        buttonSix.setTextSize(TypedValue.COMPLEX_UNIT_PX, 48F)
//        buttonSix.setOnClickListener {
//            Toast.makeText(activity, "Contents preparing...", Toast.LENGTH_SHORT).show()
//
//            if (mInterstitialAd.isLoaded) {
//                mInterstitialAd.show()
//            }
//        }
//
//        val buttonSeven = activity!!.findViewById(com.ls.dareman.R.id.menu_seven) as Button
//        buttonSeven.setTextSize(TypedValue.COMPLEX_UNIT_PX, 48F)
//        buttonSeven.setOnClickListener {
//            Toast.makeText(activity, "Contents preparing...", Toast.LENGTH_SHORT).show()
//
//            if (mInterstitialAd.isLoaded) {
//                mInterstitialAd.show()
//            }
//        }
    }

    private fun initInterstitialAds() {
        mInterstitialAd = InterstitialAd(this.activity)
        // debug
//        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        // prod
        mInterstitialAd.adUnitId = getString(com.ls.dareman.R.string.interstitial_ad_unit_id)
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        mInterstitialAd.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                mInterstitialAd.loadAd(AdRequest.Builder().build())
            }
        }
    }

}