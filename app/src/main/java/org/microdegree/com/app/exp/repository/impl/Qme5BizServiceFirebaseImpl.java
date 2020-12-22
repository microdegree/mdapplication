package org.microdegree.com.app.exp.repository.impl;

import androidx.lifecycle.LiveData;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
/*import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.FirebaseFunctionsException;
import com.google.firebase.functions.HttpsCallableResult;
import com.google.firebase.iid.FirebaseInstanceId;*/

import org.microdegree.com.app.exp.db.Qme5BizDao;
import org.microdegree.com.app.exp.db.Qme5BizDb;
import org.microdegree.com.app.exp.repository.EventRepoI;
import org.microdegree.com.app.exp.util.Constants;
import org.microdegree.com.app.exp.vo.CourseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Rakesh on 1/20/18.
 */

public class Qme5BizServiceFirebaseImpl implements EventRepoI {

    private static final String TAG = "Qme5BizFirebaseImpl";
    private static final DatabaseReference EVENT_LIST_REF =
            FirebaseDatabase.getInstance().getReference();
    private final Qme5BizDb db;
    private final Qme5BizDao qme5BizDao;
    private Retrofit retrofit;



    //private FirebaseFunctions mFunctions;


    @Inject
    public Qme5BizServiceFirebaseImpl(Qme5BizDb db, Qme5BizDao qme5BizDao, Retrofit retrofit) {
        this.db = db;
        this.qme5BizDao = qme5BizDao;
        this.retrofit=retrofit;

    }


    // ---------------------- Cable Bhai Related COde Starts -------------- //


    private Observable<DataSnapshot> channelsObservable(){
        return Observable.create(emitter -> {
            EVENT_LIST_REF.child(Constants.COURSES).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Log.d(TAG, "COURSES channelsObservable is: " + dataSnapshot);

                    emitter.onNext(dataSnapshot);

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "COURSES channelsObservable Failed to read value.", error.toException());
                }
            });
        });
    }


    // --------------------------   Observers Start  --------------------------

    @Override
    public void runInitialObservables(){

        channelsObservable().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(channels -> {
                            List<CourseModel> courseModelList = new ArrayList<>();
                            try{
                                Log.d(TAG, "course List" + channels);
                                for(DataSnapshot data1 : channels.getChildren()){
                                    //Log.d(TAG, "Individual Channels data1" + data1);
                                    CourseModel courseModel = data1.getValue(CourseModel.class);
                                    // Log.d(TAG, "Individual Channels" + channel);
                                    courseModelList.add(courseModel);
                                }
                                saveCourseModelResult(courseModelList);
                            }catch(Exception e){
                                Log.e(TAG," Error while getChannelList " + e.getMessage());
                            }
                        }
                );

    }






    // --------------------------   Save Logic  Start  --------------------------

    private void saveCourseModelResult(List<CourseModel> courseModelList) {
        Log.d(TAG, "saveWaitStatusCallResultIn save callResult waitStatusByBizId" + courseModelList);
        db.beginTransaction();
        try {
            qme5BizDao.deleteCourses();
            qme5BizDao.insertCourses(courseModelList);
            db.setTransactionSuccessful();
        } finally {
            Log.d(TAG, " saveWaitStatusCallResult Successfull  saveWaitListCallResult Update ");
            db.endTransaction();

            Log.d(TAG,"inserted success "+qme5BizDao.getCourseListCheck());
        }
    }

    // --------------------------   Logic Start  --------------------------

    @Override
    public LiveData<List<CourseModel>> getCoursesList(String header , Map<String,Object> searchQueryMap) {

        return runFilterQuery1(header , searchQueryMap);
        //return qme5BizDao.getChannelListByHeader(header);
    }

    // --------------------------   Helper Methods  --------------------------

    //TODO:IMP Exception handling
    //TODO:Check if any performance impact for use of instance variables for storing the list


    private LiveData<List<CourseModel>> runFilterQuery1(String header ,Map<String,Object> searchQueryMap){

        //Log.d(TAG,"course List runFilterQuery1 :  " + qme5BizDao.getCourseList(header));

        return qme5BizDao.getCourseList(header);

        //return qme5BizDao.getCourseListFree();
    }

    }
