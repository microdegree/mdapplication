package org.microdegree.com.app.exp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import org.microdegree.com.app.exp.vo.CourseModel;

import java.util.List;

/**
 * Created by Rakesh on 11/27/17.
 */

@Dao
public interface Qme5BizDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourses(List<CourseModel> courseModelList);

    @Query("DELETE FROM coursemodel")
    void deleteCourses();

    @Query("SELECT * from coursemodel where courseType=:header")
    LiveData<List<CourseModel>> getCourseList(String header);

    /*@Query("SELECT * from coursemodel where courseType='free'")
    LiveData<List<CourseModel>> getCourseListFree();*/

    @Query("SELECT * from coursemodel")
   List<CourseModel> getCourseListCheck();

}
