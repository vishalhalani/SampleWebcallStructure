/*
 * Copyright 2016, The Digicorp Information Systems Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vishal.samplewebcallstructure.helper.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.vishal.samplewebcallstructure.helper.api.response.ErrorResponse;
import com.vishal.samplewebcallstructure.model.basemodel.BaseResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Callback adapter
 */
public abstract class CallbackAdapter<T> implements Callback<BaseResponse<T>> {
    private static Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    @Override
    public void onResponse(Call<BaseResponse<T>> call, Response<BaseResponse<T>> response) {
        if (call.isCanceled()) {
            return;
        }

        if (!response.isSuccessful()) {
            ErrorResponse errorResponse;
            try {
                if (response.code() == 401) {
                    JsonObject errJsonObj = gson.fromJson(response.errorBody().toString(), JsonObject.class);
                    errorResponse = ErrorResponse.fromJson(errJsonObj.get("error").getAsJsonObject());
//                    showAlert(JobCardApplication.getInstant().getApplicationContext());
                } else if (response.code() == 402) {
                    JsonObject errJsonObj = gson.fromJson(response.errorBody().toString(), JsonObject.class);
//                    String resp = errJsonObj.get("error").getAsJsonObject().toString();
                    errorResponse = ErrorResponse.fromJson(errJsonObj.get("error").getAsJsonObject());
//                    showBlockScreen(JobCardApplication.getInstant().getApplicationContext(), resp);
                } else {
                    errorResponse = new ErrorResponse(response.errorBody().toString());
                }
//                if (response.code() == 401) {
////                    JsonObject errJsonObj = gson.fromJson(response.errorBody().string(), JsonObject.class);
////                    errorResponse = ErrorResponse.fromJson(errJsonObj.get("error").getAsJsonObject());
////                    apiError.setMessage(response.errorBody().string());
//
//                    showAlert(AppController.getAppInstance().getApplicationContext());
//                } else if (response.code() == 402) {
////                    JsonObject errJsonObj = gson.fromJson(response.errorBody().string(), JsonObject.class);
////                    String resp = errJsonObj.get("error").getAsJsonObject().toString();
////                    errorResponse = ErrorResponse.fromJson(errJsonObj.get("error").getAsJsonObject());
////                    apiError.setMessage(response.errorBody().string());
//                    showBlockScreen(AppController.getAppInstance().getApplicationContext(), apiError.getMessage());
//                }
//                else {
//                    apiError = new APIError(response.errorBody().string());
//                }
            } catch (Exception e) {
                errorResponse = ErrorResponse.unknownError();
            }
            onApiResponse(errorResponse, null);
            return;
        }

        BaseResponse baseResponse = response.body();


        if (baseResponse == null) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            onApiResponse(ErrorResponse.unknownError(), null);
            return;
        }

        if (baseResponse.hasStatus()) {
            boolean status = baseResponse.getStatusAsBoolean();
            if (!status) {
                try {
//                    System.out.println("body " + baseResponse);
                    ErrorResponse errorResponse = new ErrorResponse(baseResponse.getMessage());

                    onApiResponse(errorResponse, null);
                } catch (Exception e) {

                    ErrorResponse errorResponse = new ErrorResponse("Something went wrong, Please try again.");


                    onApiResponse(errorResponse, null);
                }
            } else {

                onApiResponse(null, baseResponse);
            }
        } else {


            ErrorResponse errorResponse = new ErrorResponse("Something went wrong, Please try again.");
            onApiResponse(errorResponse, null);
        }
    }

    @Override
    public void onFailure(Call<BaseResponse<T>> call, Throwable t) {
//        ErrorResponse errorResponse = new ErrorResponse(-2, t.getLocalizedMessage());
//        ErrorResponse errorResponse = new ErrorResponse("Please check your internet connection.");


        ErrorResponse errorResponse = new ErrorResponse("Seems like connectivity issue. Please try again.");
        onApiResponse(errorResponse, null);
    }

//    public void showAlert(final Context context) {
//        if (!UtilsPref.getAppPrefBool(KEY_LOGIN, false)) {
//            return;
//        }
//        try {
//            UtilsPref.writeSharedPreferencesBool(KEY_LOGIN, false);
//            Intent intent = new Intent(context, LogOutActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            context.startActivity(intent);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void showBlockScreen(final Context context, String response) {
//        try {
//            Intent intent = new Intent(context, WelcomeActivity.class);
//            intent.putExtra(EXTRA_SHOW, true);
//            intent.putExtra("Response", response);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            context.startActivity(intent);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public abstract void onApiResponse(ErrorResponse err, BaseResponse baseResponse);
}
