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



import com.vishal.samplewebcallstructure.model.basemodel.BaseResponse;

import java.util.WeakHashMap;

import retrofit2.Call;

/**
 * Helper class to execute api call and can be useful to cancel it later if required.<br />
 * example: <br />
 * <pre><code>
 * public void onCreate() {
 *   ...
 *   Call&lt;JsonObject&gt; call = apiClient.getCategories();
 *   ApiCallManager.enqueue("get_categories", call, new CallbackAdapter() {
 *     public void onApiResponse(ErrorResponse err, BaseResponse response) {
 *       // handle api response
 *     }
 *   });
 * }
 *
 * public void onStop() {
 *   ApiCallManager.cancelCall("get_categories");
 *   super.onStop();
 * }
 * </code><pre>
 */

public class ApiCallManager {
    private static WeakHashMap<String, Object> apiCallMap = new WeakHashMap<>();

    public static <T> void enqueue(String tag, Call<BaseResponse<T>> call, CallbackAdapter<T> callbackAdapter) {

        if (tag == null) {
            throw new IllegalArgumentException("\"tag\" can't be null");
        }
        if (call == null) {
            throw new IllegalArgumentException("\"call\" can't be null");
        }
        if (call.isCanceled()) {
            throw new IllegalArgumentException("Can't enqueue canceled call");
        }
        cancelCall(tag);
        executeCall(call, callbackAdapter);
        apiCallMap.put(tag, call);
    }

    public static void cancelCall(String tag) {
        if (!apiCallMap.containsKey(tag))
            return;

        Call call = (Call) apiCallMap.get(tag);
        if (call == null || call.isCanceled())
            return;
        if (!call.isExecuted()) {
            call.cancel();
        }
        apiCallMap.remove(tag);
    }

    private static <T> void executeCall(Call<BaseResponse<T>> call, CallbackAdapter<T> callbackAdapter) {
        call.enqueue(callbackAdapter);
    }


}
