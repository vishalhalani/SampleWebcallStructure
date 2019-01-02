package com.vishal.samplewebcallstructure.helper.api.interfaces;


import com.vishal.samplewebcallstructure.helper.api.response.ErrorResponse;
import com.vishal.samplewebcallstructure.model.basemodel.BaseResponse;

public interface OnWebAPIResponseListener {

    void onCallComplete(BaseResponse baseResponse, int requestCode);


    // void onCallComplete(List<?> obj, int requestCode);

    void onCallError(ErrorResponse errorModel, int requestCode);

}
