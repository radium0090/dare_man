package com.ls.dareman;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.ls.dareman.Utils.DialogFragmentHelper;


public class SplashActivity extends FragmentActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private AdView mAdView;

    public static final String[] HOST_URL = {
            "https://550909.com?friend=1472045",
            "https://mintj.com/?mdc=991&afguid=tx0icnka6n4i20rfvwa3guaos",
            "https://meru-para.com/?mdc=991&afguid=tlghvwiy7utr2idztdy46fg90",
            "https://mobile.digicafe.jp/r/vAKDTV",
            "https://pcmax.jp/lp/?ad_id=rm296877",
            "https://www.194964.com/AF1219473",
            "https://aso-bo.com/index.php?nb=v79958767"
    };

    public static final String[] ITEMS = {
            "  ⭐⭐　ワクワクメール️",
            "  ⭐️️⭐　Jメール️",
            "  ⭐️⭐　メルパラ️",
            "  ⭐️️⭐　️デジカフェ",
            "  ⭐️️⭐️　PC-MAX",
            "  ⭐️️⭐️　イククル",
            "  ⭐️️⭐　ASOBO"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//        setChannel();
        initView();
        initAds();

        // for debug
//        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener((task) -> {
//            if (!task.isSuccessful()) {
//                Log.w("FIREBASE", "getInstanceId failed.", task.getException());
//                return;
//            }
//
//            String token = (task.getResult() == null) ? "empty" : task.getResult().getToken();
//            Log.i("FIREBASE", "[CALLBACK] Token = " + token);
//        });

        onDomainSelector();
        getDynamicLinks();

        // load Admob
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void initView() {
        TextView textView = (TextView)findViewById(R.id.titleText);
        textView.setText(getString(R.string.splash_title));
        textView.setTextColor(Color.parseColor("#003262"));
        textView.setTextSize(27);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        TextPaint paint = textView.getPaint();
        paint.setFakeBoldText(true);

        TextView textView1 = (TextView)findViewById(R.id.titleSubText);
        textView1.setText(getString(R.string.splash_sub_title));
        textView1.setTextColor(Color.parseColor("#003262"));
        textView1.setTextSize(17);
        textView1.setGravity(Gravity.CENTER_HORIZONTAL);
        TextPaint paint1 = textView1.getPaint();
        paint1.setFakeBoldText(true);
    }

    public void onDomainSelector() {
        String title = getString(R.string.splash_dialog_title);
        DialogFragmentHelper.listDialog(getSupportFragmentManager(), title, ITEMS, result -> {

            String loadUrl = HOST_URL[result];
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.putExtra("id", result);
            intent.putExtra("url", loadUrl);

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(result));
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, ITEMS[result]);
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "url");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

            startActivity(intent);
            finish();

        }, false);
    }

//    private void setChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//
//            NotificationChannel channel = new NotificationChannel(
//                    "dm_push",
//                    "DMからのプッシュ通知",
//                    NotificationManager.IMPORTANCE_DEFAULT
//            );
//
//            // 通知時にライトを有効にする
//            channel.enableLights(true);
//            // 通知時のライトの色
//            channel.setLightColor(Color.WHITE);
//            // ロック画面での表示レベル
//            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
//
//            // チャンネルの登録
//            manager.createNotificationChannel(channel);
//        }
//    }

    private void initAds() {
        MobileAds.initialize(this, getString(R.string.ADMOB_APP_ID));
    }

    public void getDynamicLinks() {
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, pendingDynamicLinkData -> {
                    // Get deep link from result (may be null if no link is found)
                    Uri deepLink = null;
                    if (pendingDynamicLinkData != null) {
                        deepLink = pendingDynamicLinkData.getLink();
                        Log.i("DYNAMIC-LINKS", deepLink.toString());
                    }


                    // Handle the deep link. For example, open the linked
                    // content, or apply promotional credit to the user's
                    // account.
                    // ...

                    // ...
                })
                .addOnFailureListener(this, e -> Log.w("DYNAMIC-LINKS", "getDynamicLink:onFailure", e));
    }
}
