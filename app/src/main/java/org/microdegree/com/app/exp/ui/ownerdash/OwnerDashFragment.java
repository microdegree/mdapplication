package org.microdegree.com.app.exp.ui.ownerdash;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import androidx.databinding.DataBindingUtil;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import org.apache.commons.collections4.MapUtils;
import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.binding.FragmentDataBindingComponent;
import org.microdegree.com.app.exp.databinding.OwnerDashboardFragmentBinding;
import org.microdegree.com.app.exp.di.Injectable;
import org.microdegree.com.app.exp.ui.common.NavigationController;
import org.microdegree.com.app.exp.ui.common.WaitListCustomerAdapter;
import org.microdegree.com.app.exp.util.AutoClearedValue;
import org.microdegree.com.app.exp.util.Constants;
import org.microdegree.com.app.exp.vo.CourseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Rakesh on 7/3/18.
 */

public class OwnerDashFragment extends Fragment implements Injectable {

    public static final String TAG = "OwnerDashFragment.class";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    NavigationController navigationController;

    androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    public void setSearchQueryMap(Map<String, Object> searchQueryMap) {
        this.searchQueryMap = searchQueryMap;
    }

    private Map<String,Object> searchQueryMap = new HashMap<>();


    AutoClearedValue<OwnerDashboardFragmentBinding> binding;

   // AutoClearedValue<WaitListCustItemAdapter> adapter;
    private OwnerDashViewModel ownerDashViewModel;

    AutoClearedValue<WaitListCustomerAdapter> waitListCustomerAdapter;

//TODO:Code for removing observing.Possible Memory leak

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.d(TAG,"In onCreateView Activity");
        //hideOtherButtonsPostFilter();

        OwnerDashboardFragmentBinding dataBinding = DataBindingUtil
                .inflate(inflater, R.layout.owner_dashboard_fragment, container, false,
                        dataBindingComponent);
        binding = new AutoClearedValue<>(this, dataBinding);

        return dataBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG,"In onActivityCreated Activity");


        ownerDashViewModel = ViewModelProviders.of(this.getActivity(), viewModelFactory).get(OwnerDashViewModel.class);


        //TODO : create this during app installation or first app login
        headerClickListeners();

        WaitListCustomerAdapter waitListCustomerAdapter1 = new WaitListCustomerAdapter(dataBindingComponent,null,ownerDashViewModel,this);
        //binding.get().eventList.setAdapter(waitListCustomerAdapter1);
        waitListCustomerAdapter = new AutoClearedValue<>(this, waitListCustomerAdapter1);
        initRecyclerView(ownerDashViewModel);

    }

    private void initRecyclerView(OwnerDashViewModel ownerDashViewModel) {

        Log.d(TAG,"In initRecycler view ");

        ownerDashViewModel.getCoursesList().observe(this,channelList -> {

            Log.d(TAG,"ownerDashViewModel.searchQueryMap "+ownerDashViewModel.searchQueryMap.getValue());
            Log.d(TAG,"channelList "+channelList);

            List<Object> cableChannelList = new ArrayList<>();
            cableChannelList.addAll(channelList);
                Log.d(TAG,"Setting Free/Paid Adapter ");
                waitListCustomerAdapter.get().items = cableChannelList;
                binding.get().eventList.setAdapter(waitListCustomerAdapter.get());
        });

    }

    private void headerClickListeners(){

        Button tab1 =  (Button) this.getActivity().findViewById(R.id.tab1);
        Button tab2 =  (Button) this.getActivity().findViewById(R.id.tab2);
        Button tab3 =  (Button) this.getActivity().findViewById(R.id.tab3);


        Log.d(TAG,"header listener "+searchQueryMap);
        if(MapUtils.isEmpty(searchQueryMap) || null==searchQueryMap.get(Constants.HEADER)){

            //resetAllLayouts();
            //binding.get().packageLayoutScroller.setVisibility(View.VISIBLE);

            //setFreeItems();
            binding.get().webview1.setVisibility(View.INVISIBLE);
            searchQueryMap.put(Constants.HEADER,Constants.FREE);
            ownerDashViewModel.searchQueryMap.setValue(searchQueryMap);
            Log.d(TAG,"header listener2 "+searchQueryMap);


//            tab1.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));
//            tab2.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));
//            tab3.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));

        }

        tab1.setOnClickListener(v-> {
            //resetAllLayouts();
            //binding.get().packageLayoutScroller.setVisibility(View.VISIBLE);
            binding.get().webview1.setVisibility(View.INVISIBLE);
            searchQueryMap.put(Constants.HEADER,Constants.FREE);
            ownerDashViewModel.searchQueryMap.setValue(searchQueryMap);

//            tab1.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));
//            tab2.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));
//            tab3.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));

            //setFreeItems();
        });

        tab2.setOnClickListener(v-> {
            //resetAllLayouts();
            // binding.get().packageLayoutScroller.setVisibility(View.VISIBLE);
            binding.get().webview1.setVisibility(View.INVISIBLE);
//        binding.get().eventList.setVisibility(View.VISIBLE);
            searchQueryMap.put(Constants.HEADER,Constants.PAID);
            ownerDashViewModel.searchQueryMap.setValue(searchQueryMap);

////            tab1.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.purple_orchid));
//            tab1.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));
//            tab2.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));
////            tab2.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
//            tab3.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));

            //setPaidItems();
        });

        tab3.setOnClickListener(v-> {
            binding.get().webview1.setVisibility(View.VISIBLE);
//            tab1.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));
//            tab2.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));
////            tab3.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));
//            tab3.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.darkblue));


            WebView webView = (WebView) this.getActivity().findViewById(R.id.webview1);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            webView.loadUrl("https://blog.microdegree.work/");

        });
    }

    private void launchChromeTab(String url){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this.getContext(), Uri.parse(url));
    }

    Map<String, CourseModel> courseModelMap = new HashMap<>();

    private void createCourseModel(){

    }

}