package org.microdegree.com.app.exp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.microdegree.com.app.exp.di.AppInjector;
import org.microdegree.com.app.exp.util.LocaleManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Rakesh on 11/26/17.
 */

public class Qme5BizApp extends Application implements HasActivityInjector {

    public static final String TAG = "Qme5BizApp.class";

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        /*DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);*/

     /*   if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }*/

       AppInjector.init(this);
        AndroidThreeTen.init(this);

        MobileAds.initialize(this, "ca-app-pub-6120582676264206~4312328456");


    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
        Log.d(TAG, "attachBaseContext");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
        Log.d(TAG, "onConfigurationChanged: " + newConfig.locale.getLanguage());

    }
}
