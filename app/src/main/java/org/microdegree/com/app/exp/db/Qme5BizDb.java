package org.microdegree.com.app.exp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import org.microdegree.com.app.exp.vo.CourseModel;

/**
 * Created by Rakesh on 11/27/17.
 */

@Database(entities = {CourseModel.class},version = 10 , exportSchema = false)
@TypeConverters({Converters.class})
public abstract class Qme5BizDb extends RoomDatabase{

    abstract public Qme5BizDao qme5BizDao();
}
