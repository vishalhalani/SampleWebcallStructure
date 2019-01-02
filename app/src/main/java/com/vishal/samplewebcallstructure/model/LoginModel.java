package com.vishal.samplewebcallstructure.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vishal.samplewebcallstructure.model.basemodel.BaseDetailModel;

public class LoginModel extends BaseDetailModel {

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;


    public LoginModel(String email, String password, String api_token, String udid, String device_type) {
        super(api_token, udid, device_type);
        this.email = email;
        this.password = password;
    }
}
