package org.microdegree.com.app.exp.ui.common;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.databinding.WaitListItemBinding;
import org.microdegree.com.app.exp.ui.ownerdash.OwnerDashFragment;
import org.microdegree.com.app.exp.ui.ownerdash.OwnerDashViewModel;
import org.microdegree.com.app.exp.util.Constants;
import org.microdegree.com.app.exp.vo.CourseModel;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

/**
 * Created by Rakesh on 11/21/18.
 */

public class WaitListCustomerAdapter extends RecyclerView.Adapter<DataBoundViewHolder>{

    public static final String TAG = "WListCItmAdptr.class";

    // A menu item view type.
    private static final int CHANNEL_ITEM_VIEW_TYPE = 0;

    // The banner ad view type.
    private static final int BANNER_AD_VIEW_TYPE = 1;


    @NonNull
    public List<Object> items = Collections.emptyList();


    private final androidx.databinding.DataBindingComponent dataBindingComponent;
    private final WaitListCustomerAdapter.WaitListDetailsClickCallBack waitListDetailsClickCallBack;

    private OwnerDashViewModel ownerDashViewModel;

    private OwnerDashFragment fragment ;

    public WaitListCustomerAdapter(androidx.databinding.DataBindingComponent dataBindingComponent, WaitListCustomerAdapter.WaitListDetailsClickCallBack waitListDetailsClickCallBack, OwnerDashViewModel ownerDashViewModel, OwnerDashFragment fragment) {

        Log.d(TAG,"In WaitListItemAdapter List Adapter Activity");

        this.dataBindingComponent = dataBindingComponent;
        this.waitListDetailsClickCallBack=waitListDetailsClickCallBack;
        this.ownerDashViewModel = ownerDashViewModel;

        this.fragment = fragment;
    }


    public interface WaitListDetailsClickCallBack {
        //void onClick(Customer customer);
    }


    @Override
    public DataBoundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                WaitListItemBinding waitListItemBinding = DataBindingUtil
                        .inflate(LayoutInflater.from(parent.getContext()), R.layout.wait_list_item,
                                parent, false, dataBindingComponent);
                DataBoundViewHolder dataBoundViewHolder = new DataBoundViewHolder(waitListItemBinding);

        waitListItemBinding.getRoot().setOnClickListener(v-> {

            Log.d(TAG," url courseName" + waitListItemBinding.getCourse().getCourseName());
            String url = waitListItemBinding.getCourse().getCourseUri();
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(fragment.getActivity(), Uri.parse(url));


            //waitListDetailsClickCallBack.onClick();

        });

                return dataBoundViewHolder;
    }


    @Override
    public void onBindViewHolder(DataBoundViewHolder holder, int position) {

                CourseModel courseModel = (CourseModel) items.get(position);
                WaitListItemBinding waitListItemBinding = (WaitListItemBinding) holder.binding;
                waitListItemBinding.setCourse(courseModel);


                if(null!=courseModel.getCoursePrice()){
                    waitListItemBinding.wListItemrupeeIcon.setVisibility(View.VISIBLE);
                }else{
                    waitListItemBinding.wListItemrupeeIcon.setVisibility(View.GONE);
                }
        Log.d(TAG, "onBindViewHolder: glide " + courseModel);
                //TODO: Map image location

        if(null!= courseModel.getCourseImageUrl()){
            Glide.with(fragment)
                    .load(Uri.parse(courseModel.getCourseImageUrl()))
                    .into(waitListItemBinding.tvChannelLogo);
        }else{
            Glide.with(fragment)
                    .load(Uri.parse("https://firebasestorage.googleapis.com/v0/b/md-android-f745b.appspot.com/o/md-android%2Faiml.jpg?alt=media&token=19fd7835-fa0f-4cab-96a0-d546d5f4dadc"))
                    .into(waitListItemBinding.tvChannelLogo);
        }



    }



    @Override
    public int getItemViewType(int position) {

        if(position == 0){
            return CHANNEL_ITEM_VIEW_TYPE;
        }

        return (position % Constants.ITEMS_PER_AD == 0) ? BANNER_AD_VIEW_TYPE
                : CHANNEL_ITEM_VIEW_TYPE;
    }


    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    //TODO : Make the set contain only channel Id  instead of the whole object



}

