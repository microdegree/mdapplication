package org.microdegree.com.app.exp.ui.ownerdash;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import android.util.Log;

import org.microdegree.com.app.exp.repository.impl.Qme5BizServiceFirebaseImpl;
import org.microdegree.com.app.exp.util.AbsentLiveData;
import org.microdegree.com.app.exp.util.Constants;
import org.microdegree.com.app.exp.vo.CourseModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Rakesh on 7/3/18.
 */

public class OwnerDashViewModel extends ViewModel {

    public static final String TAG = "OwnerDashVM.class";

    public final MutableLiveData<Map<String,Object>> searchQueryMap = new MutableLiveData<>();

    Qme5BizServiceFirebaseImpl qme5BizServiceFirebase;

    private final LiveData<List<CourseModel>> coursesList;

    private String headerSelected = Constants.FREE;

    @Inject
    public OwnerDashViewModel(Qme5BizServiceFirebaseImpl qme5BizServiceFirebase) {

        Log.d(TAG, "In OwnerDashViewModel ");

        this.qme5BizServiceFirebase = qme5BizServiceFirebase;

/*
        this.userSelection.postValue(new UserSelection());
*/

        qme5BizServiceFirebase.runInitialObservables();


        coursesList = Transformations.switchMap(searchQueryMap, input -> {
            if (input.isEmpty()) {

                Log.d(TAG, "bizId custList Executing nothing here ");

                return AbsentLiveData.create();
            } else {

                Log.d(TAG, "coursesList Executing " + input);

                headerSelected = input.get(Constants.HEADER).toString();

                return qme5BizServiceFirebase.getCoursesList(input.get(Constants.HEADER).toString(),searchQueryMap.getValue());
            }

        });

    }

    public LiveData<List<CourseModel>> getCoursesList() {
        return coursesList;
    }


    public String getHeaderSelected() {
        return headerSelected;
    }


}
