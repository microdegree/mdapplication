package org.microdegree.com.app.exp.vo;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"courseId"})
@IgnoreExtraProperties
public class CourseModel implements Serializable {

    @SerializedName("courseId")
    @NonNull
    private String courseId;

    @SerializedName("courseName")
    private String courseName;

    @SerializedName("coursePrice")
    private String coursePrice;

    @SerializedName("courseType")
    private String courseType; //free or paid



    @SerializedName("courseImageUrl")
    private String courseImageUrl;


    @SerializedName("courseUri")
    private String courseUri;



    @SerializedName("courseSkills")
    private String courseSkills;

    public CourseModel() {
    }

    @NonNull
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(@NonNull String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseImageUrl() {
        return courseImageUrl;
    }

    public void setCourseImageUrl(String courseImageUrl) {
        this.courseImageUrl = courseImageUrl;
    }

    public String getCourseUri() {
        return courseUri;
    }

    public void setCourseUri(String courseUri) {
        this.courseUri = courseUri;
    }

    public String getCourseSkills() {
        return courseSkills;
    }

    public void setCourseSkills(String courseSkills) {
        this.courseSkills = courseSkills;
    }

    @Override
    public String toString() {
        return "CourseModel{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", coursePrice='" + coursePrice + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseImageUrl='" + courseImageUrl + '\'' +
                ", courseUri='" + courseUri + '\'' +
                ", courseSkills='" + courseSkills + '\'' +
                '}';
    }


}