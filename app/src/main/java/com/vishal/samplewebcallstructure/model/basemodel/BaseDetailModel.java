package com.vishal.samplewebcallstructure.model.basemodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseDetailModel implements Parcelable {
    @SerializedName("api_token")
    @Expose
    private String api_token;

    @SerializedName("udid")
    @Expose
    private String udid;

    @SerializedName("device_type")
    @Expose
    private String device_type;

    public BaseDetailModel(String api_token, String udid, String device_type) {
        this.api_token = api_token;
        this.udid = udid;
        this.device_type = device_type;
    }


    public static final Creator<BaseDetailModel> CREATOR = new Creator<BaseDetailModel>() {
        @Override
        public BaseDetailModel createFromParcel(Parcel in) {
            return new BaseDetailModel(in);
        }

        @Override
        public BaseDetailModel[] newArray(int size) {
            return new BaseDetailModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    protected BaseDetailModel(Parcel in) {
        this.api_token = in.readString();
        this.udid = in.readString();
        this.device_type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.api_token);
        dest.writeString(this.udid);
        dest.writeString(this.device_type);
    }

}