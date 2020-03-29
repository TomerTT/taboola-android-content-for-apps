package com.taboola.android.content_for_apps_sample;

import android.app.Application;

import com.taboola.android.js.TaboolaJs;

public class TaboolaSampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        /*
         * Taboola Integration Verifier is a special mode that helps you check your Taboola
         * SDK integration into your project
         *
         * Important: Do not distribute your app with the verifier enabled.
         *
         * Use the following link for more information about the Integration Verifier:
         * https://sdk.taboola.com/docs/android-sdk-integration-verifier
         *
         * Uncomment the line below to enable this tool.
         */

        // IntegrationVerifier.getInstance().verifyIntegration(this, true);

        // Required when using TaboolaJS integration
        TaboolaJs.getInstance().init(getApplicationContext());
    }
}