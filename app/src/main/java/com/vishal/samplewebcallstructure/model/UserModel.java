package com.vishal.samplewebcallstructure.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("user_id")
    @Expose
    private int user_id;

    @SerializedName("first_name")
    @Expose
    private String first_name;


    @SerializedName("email")
    @Expose
    private String email;


    @SerializedName("phone_number")
    @Expose
    private String phone_number;


    @SerializedName("api_auth_token")
    @Expose
    private String api_auth_token;

    @SerializedName("profile_pic_url")
    @Expose
    private String profile_pic_url;




    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getApi_auth_token() {
        return api_auth_token;
    }

    public void setApi_auth_token(String api_auth_token) {
        this.api_auth_token = api_auth_token;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }
}
