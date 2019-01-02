package com.vishal.samplewebcallstructure.controller;

import android.content.Context;


import com.vishal.samplewebcallstructure.helper.ApiConst;
import com.vishal.samplewebcallstructure.helper.PrefManager;
import com.vishal.samplewebcallstructure.helper.api.ApiCallManager;
import com.vishal.samplewebcallstructure.helper.api.CallbackAdapter;
import com.vishal.samplewebcallstructure.helper.api.interfaces.ApiClient;
import com.vishal.samplewebcallstructure.helper.api.interfaces.OnWebAPIResponseListener;
import com.vishal.samplewebcallstructure.helper.api.response.ErrorResponse;
import com.vishal.samplewebcallstructure.model.LoginModel;
import com.vishal.samplewebcallstructure.model.UserModel;
import com.vishal.samplewebcallstructure.model.basemodel.BaseModel;
import com.vishal.samplewebcallstructure.model.basemodel.BaseResponse;

import retrofit2.Call;

public class LoginController {

    ApiClient apiService = null;
    OnWebAPIResponseListener mRepsonseListener;
    Context context;

    public LoginController(OnWebAPIResponseListener mRepsonseListener, Context context) {
        this.context = context;
        this.mRepsonseListener = mRepsonseListener;
        apiService = AppController.getAppInstance().getApiClient();
    }


    /**
     * This method handle login request
     *
     * @param requestCode to identify request
     */
    public void loginUser(final int requestCode, String email, String password) {
        BaseModel<LoginModel> baseModel = new BaseModel<>(ApiConst.USER_LOGIN, new LoginModel(email, password, PrefManager.getAccessToken(context), PrefManager.getDeviceId(context), ApiConst.DEVICE_TYPE));


        Call<BaseResponse<UserModel>> call = apiService.loginUser(baseModel);

        ApiCallManager.enqueue(ApiConst.USER_LOGIN, call, new CallbackAdapter<UserModel>() {
            @Override
            public void onApiResponse(ErrorResponse err, BaseResponse baseResponse) {

                if (err != null) {
                    mRepsonseListener.onCallError(err, requestCode);
                }

                if (baseResponse != null && baseResponse.getData() != null) {

                    storeInPreference(baseResponse, requestCode);

                }
            }
        });


    }

    // store user data in shared preference

    private void storeInPreference(BaseResponse baseResponse, int requestCode) {


        if (baseResponse.hasStatus()) {


            if (baseResponse.getData() instanceof UserModel) {
                UserModel userModel = (UserModel) baseResponse.getData();

                if (userModel != null) {
                    PrefManager.setUserVerified(context, true);
                    PrefManager.setUserLoggedIn(context, true);
                    PrefManager.setUserName(context, userModel.getFirst_name());
                    PrefManager.setAuthToken(context, userModel.getApi_auth_token());
                    PrefManager.setUserId(context, userModel.getUser_id());
                    PrefManager.setUserEmail(context, userModel.getEmail());
                    PrefManager.setUserImage(context, userModel.getProfile_pic_url());

                    PrefManager.setIsFirstTime(context, false);


                    mRepsonseListener.onCallComplete(baseResponse, requestCode);
                }


            }


        }
    }
}


