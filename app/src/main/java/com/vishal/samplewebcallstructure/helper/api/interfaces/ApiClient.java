package com.vishal.samplewebcallstructure.helper.api.interfaces;


import com.vishal.samplewebcallstructure.helper.ApiConst;
import com.vishal.samplewebcallstructure.model.UserModel;
import com.vishal.samplewebcallstructure.model.basemodel.BaseModel;
import com.vishal.samplewebcallstructure.model.basemodel.BaseResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiClient {
    String URL = ApiConst.BASE_URL;



    @POST(URL + ApiConst.SUBSITE_URL)
    Call<BaseResponse<UserModel>> registerUser(@Body BaseModel model);

    @POST(URL + ApiConst.SUBSITE_URL)
    Call<BaseResponse<UserModel>> loginUser(@Body BaseModel model);

    @POST(URL + ApiConst.SUBSITE_URL)
    Call<BaseResponse<Void>> forgotPassword(@Body BaseModel model);

    @POST(URL + ApiConst.SUBSITE_URL)
    Call<BaseResponse<Void>> resetPassword(@Body BaseModel model);



}
