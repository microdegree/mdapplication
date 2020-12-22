package org.microdegree.com.app.exp.vo;


import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"channelId"})
@IgnoreExtraProperties
public class Price implements Serializable {

        @SerializedName("channelId")
        @NonNull
        private String channelId;

        @SerializedName("price")
        private String price;

        public Price() {
        }

        public String getChannelId() {
                return channelId;
        }

        public void setChannelId(String channelId) {
                this.channelId = channelId;
        }

        public String getPrice() {
                return price;
        }

        public void setPrice(String price) {
                this.price = price;
        }

        @Override
        public String toString() {
                return "Price{" +
                        ", channelId='" + channelId + '\'' +
                        ", price='" + price + '\'' +
                        '}';
        }
}
