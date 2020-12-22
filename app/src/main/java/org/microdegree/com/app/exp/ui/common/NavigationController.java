package org.microdegree.com.app.exp.ui.common;

import androidx.fragment.app.FragmentManager;
import android.util.Log;

import org.microdegree.com.app.exp.MainActivity;
import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.ui.ownerdash.OwnerDashFragment;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Rakesh on 11/25/17.
 */

public class NavigationController {

    public static final String TAG = "NavigationControl.class";

    private final int containerId;
    private final FragmentManager fragmentManager;

    public Map<String, Object> getSearchQueryMap() {
        return searchQueryMap;
    }

    private Map<String, Object> searchQueryMap = new HashMap<>();

    @Inject
    public NavigationController(MainActivity mainActivity) {

        Log.d(TAG, "In NavigationController Activity");

        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }


    public void navigateToOwnerdashboardFragment() {

        Log.d(TAG, "In navigteToOwnerdashboardFragment  " + getSearchQueryMap());

        OwnerDashFragment ownerDashFragment = new OwnerDashFragment();
        ownerDashFragment.setSearchQueryMap(getSearchQueryMap());

        fragmentManager.beginTransaction()
                .add(ownerDashFragment,"ownerDashFragment")
                .replace(containerId, ownerDashFragment)
                .commitAllowingStateLoss();

    }

}
