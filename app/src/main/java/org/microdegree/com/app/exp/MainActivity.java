package org.microdegree.com.app.exp;

import androidx.appcompat.widget.PopupMenu;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.microdegree.com.app.exp.ui.common.NavigationController;
import org.microdegree.com.app.exp.ui.ownerdash.OwnerDashViewModel;
import org.microdegree.com.app.exp.util.Constants;
import org.microdegree.com.app.exp.util.LocaleManager;


import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import es.dmoral.toasty.Toasty;

import static com.crashlytics.android.Crashlytics.log;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector,PopupMenu.OnMenuItemClickListener {

    public static final String TAG = "MainActivity.class";

    FloatingActionButton sharebutton;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;


    @Inject
    NavigationController navigationController;

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FirebaseAnalytics mFirebaseAnalytics;

    private OwnerDashViewModel ownerDashViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        callInAppUpdate();

        final Button button = findViewById(R.id.mycourse1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url ="https://courses.microdegree.work/pages/courses";
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(getBaseContext(), Uri.parse(url));
            }
        });


        sharebutton=(FloatingActionButton)findViewById(R.id.shareChannelSelection);
        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Insert Subject here");
                String app_url = " https://play.google.com/store/apps/details?id=my.example.javatpoint";
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });


        Log.d(TAG, "In Main Activity");


       /* Toolbar myToolbar = (Toolbar) findViewById(R.id.appToolBar);
        setSupportActionBar(myToolbar);*/

        //ImageButton overflowMenu =  (ImageButton) MainActivity.this.findViewById(R.id.overflow_menu2);
        //showPopup(overflowMenu);


        Log.d(TAG, " *** PRINT REGION in BEGINNING *** " + navigationController.getSearchQueryMap().get(Constants.REGION));

        isOnline();

        ownerDashViewModel = ViewModelProviders.of(this, viewModelFactory).get(OwnerDashViewModel.class);


        if (savedInstanceState == null) {
            navigationController.navigateToOwnerdashboardFragment();
        }


    }





    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == conMgr) {
            Toasty.error(this, "No Internet connection!!!", Toast.LENGTH_LONG, true).show();
            return false;
        }

        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            Toasty.error(this, "No Internet connection!", Toast.LENGTH_LONG, true).show();
            return false;
        }


        return true;
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    private void setFireBaseAnalytics(String filterName){

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, filterName);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,Constants.FILTER_BY_TAG);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, Constants.FILTER_TYPE);
        mFirebaseAnalytics.logEvent(Constants.FILTER_TYPE, bundle);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);


        MenuInflater inflater = popup.getMenuInflater();
        popup.setOnMenuItemClickListener(this);
        inflater.inflate(R.menu.overflow_menu, popup.getMenu());

        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.privacy:

                Toasty.info(this, "Please check https://www.cablebhai.com/cable-bhai-privacy-policy/", Toast.LENGTH_LONG,true).show();

                return true;
            case R.id.credits:
                Toasty.info(this, "Please check https://www.cablebhai.com/appCredits", Toast.LENGTH_LONG, true).show();
                return true;
            default:
                return false;
        }
    }

    protected void onResume() {

        super.onResume();
        callInAppUpdate();
    }

    private final int UPDATE_REQUEST_CODE=1612;

    private void callInAppUpdate()
    {
        // Creates instance of the manager.
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);

// Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

// Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    // For a flexible update, use AppUpdateType.FLEXIBLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                // Request the update.
                try {
                    appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE,MainActivity.this,UPDATE_REQUEST_CODE);
                }
                catch (IntentSender.SendIntentException exception)
                {
                    Log.d(TAG, "callInAppUpdate: "+exception.getMessage());
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;

        if (requestCode == UPDATE_REQUEST_CODE) {
            Toast.makeText(this,"Downloading Start", Toast.LENGTH_SHORT).show();
            if (resultCode != RESULT_OK) {
                log("Update flow failed! Result code: " + resultCode);
                // If the update is cancelled or fails,
                // you can request to start the update again.
            }
        }
    }

}