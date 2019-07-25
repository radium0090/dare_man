package com.ls.dareman;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;


public class SplashActivity extends FragmentActivity {

    public static final String[] DEBUG_HOST_URL = {
            "https://dokodemo.world/",
            "https://mintj.com/?mdc=991&afguid=tx0icnka6n4i20rfvwa3guaos",
            "https://st-sub.dokodemo.world/",
            "https://st-alt.dokodemo.world/",
            "https://st-asap.dokodemo.world/",
            "https://st-design.dokodemo.world/ja/",
            "https://google.com/"
    };

    public static final String[] DEBUG_ITEMS = {
            "ワクワク",
            "Jメール",
            "メルパラ",
            "デジカフェ",
            "PC-MAX",
            "イククル",
            "ASOBO"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onDebugSelector();
    }

    public void onDebugSelector() {
        String title = "Please Select Site";
        DialogFragmentHelper.listDialog(getSupportFragmentManager(), title, DEBUG_ITEMS, new DialogResultListener<Integer>() {
            @Override
            public void onDataResult(Integer result) {

                String loadUrl = DEBUG_HOST_URL[result];
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("id", result);
                intent.putExtra("url", loadUrl);
                startActivity(intent);
                finish();

            }
        }, false);
    }
}
