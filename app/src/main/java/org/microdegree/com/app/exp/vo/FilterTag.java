package org.microdegree.com.app.exp.vo;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"channelId"})
@IgnoreExtraProperties
public class FilterTag implements Serializable {

    @SerializedName("channelId")
    @NonNull
    private String channelId;

    @SerializedName("language")
    private String language;

    @SerializedName("genre")
    private String genre;

    @SerializedName("hdType")
    private String hdType;

    @SerializedName("broadcaster")
    private String broadcaster;

    public FilterTag() {
    }

    @NonNull
    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(@NonNull String channelId) {
        this.channelId = channelId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getHdType() {
        return hdType;
    }

    public void setHdType(String hdType) {
        this.hdType = hdType;
    }

    public String getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(String broadcaster) {
        this.broadcaster = broadcaster;
    }

    @Override
    public String toString() {
        return "FilterTag{" +
                "channelId='" + channelId + '\'' +
                ", language='" + language + '\'' +
                ", genre='" + genre + '\'' +
                ", hdType='" + hdType + '\'' +
                ", broadcaster='" + broadcaster + '\'' +
                '}';
    }
}
