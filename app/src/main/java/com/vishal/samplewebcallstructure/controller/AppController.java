package com.vishal.samplewebcallstructure.controller;

import android.app.Application;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vishal.samplewebcallstructure.helper.ApiConst;
import com.vishal.samplewebcallstructure.helper.api.interfaces.ApiClient;

import net.danlew.android.joda.JodaTimeAndroid;



import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class AppController extends Application {

//    public static final int DISK_CACHE_SIZE = 10 * 1024 * 1024; // 10 MB
    private static AppController appInstance = null;
    private static Retrofit retrofit;

    private static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//            .setDateFormat(DATE_FORMATS)
//            .registerTypeAdapter(Date.class, new DateTypeDeserializer())
            .create();
    private ApiClient apiClient;




    public static AppController getAppInstance() {
        if (appInstance == null) {
            appInstance = new AppController();
        }
        return appInstance;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        Timber.plant(new Timber.DebugTree());



    }

    public ApiClient getApiClient() {
        if (apiClient == null) {
            apiClient = provideRetrofit(ApiClient.URL).create(ApiClient.class);

        }
        return apiClient;
    }

    private Retrofit provideRetrofit(String url) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(provideOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


    /**
     * @return boolean : True or false
     */

    public boolean isInternetOn() {


        ConnectivityManager connec;
        connec = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (connec != null) {
            activeNetwork = connec.getActiveNetworkInfo();
        }
        // Check for network connections
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // if connected with internet
                return activeNetwork.getState() == NetworkInfo.State.CONNECTED || activeNetwork.getState() == NetworkInfo.State.CONNECTING;
// connected to the mobile provider's data plan
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return activeNetwork.getState() == NetworkInfo.State.CONNECTED || activeNetwork.getState() == NetworkInfo.State.CONNECTING;
// connected to the mobile provider's data plan


            }
        } else {
//            buildDialog(activity);
            //  Toast.makeText(activity, "Internet Not Available ", Toast.LENGTH_LONG).show();

            return false;
        }


        return false;
    }

    private OkHttpClient provideOkHttpClient() {

        // Install the all-trusting trust manager


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();

        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
//        okhttpClientBuilder.addInterceptor(new RequestHeaderInterceptor());
        okhttpClientBuilder.addInterceptor(interceptor);


        return okhttpClientBuilder.build();
    }

}
