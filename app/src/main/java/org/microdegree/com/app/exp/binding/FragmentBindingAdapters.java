package org.microdegree.com.app.exp.binding;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

/**
 * Created by Rakesh on 11/25/17.
 */

public class FragmentBindingAdapters {

    public static final String TAG = "FrgmntBndngAdptrs.class";

    final Fragment fragment;

    @Inject
    public FragmentBindingAdapters(Fragment fragment) {

        Log.d(TAG,"In FrgmntBndngAdptrs");

        this.fragment = fragment;
    }

    //TODO : Check https://github.com/bumptech/glide/issues/2413 to fix Glide NPE Error

    @BindingAdapter("localImage")
    public void bindLocalImage(ImageView imageView, String url) {
        Log.d(TAG,"In FrgmntBndngAdptrs bindImageUrlRoundedCorner url : " + url + " | imageVIew Id : " + imageView);
        Glide.with(fragment).load(url).into(imageView);
    }
}
