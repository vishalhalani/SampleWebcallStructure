package com.vishal.samplewebcallstructure.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    static SharedPreferences sharedPreferences;


    private static String APP_PREF = "appPref";
    private static String USER_PREF = "userPref";
    private static String DEVICE_ID = "uuid";
    private static String IS_FIRST_TIME = "isFirstTime";
    private static String ACCESSTOKEN = "access_token";


    private static String LAST_SYNC_DATE = "last_sync_date";
    private static String USER_NAME = "userName";
    private static String USER_EMAIL = "email";
    private static String USER_ID = "userID";
    private static String USER_LOGGED_IN = "isLogged";

    private static String USER_AUTH_TOKEN = "auth_token";
    private static String USER_IMAGE_URL = "user_img_url";

    private static String USER_VERIFIED = "isVerified";


    public static void setDeviceId(Context context, String value) {
        sharedPreferences = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DEVICE_ID, value);
        editor.apply();
    }

    public static void setAccessToken(Context context, String value) {
        sharedPreferences = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCESSTOKEN, value);
        editor.apply();
    }


    public static void setIsFirstTime(Context context, boolean value) {
        sharedPreferences = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_FIRST_TIME, value);
        editor.apply();
    }

    public static boolean getIsFirstTime(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_FIRST_TIME, false);

    }

    public static String getAccessToken(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ACCESSTOKEN, "");

    }

    public static String getDeviceId(Context context) {

        sharedPreferences = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DEVICE_ID, "");

    }


    // ************************************ User preferences *******************************


    public static void setUserId(Context context, int value) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USER_ID, value);
        editor.apply();
    }

    public static int getUserId(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(USER_ID, -1);

    }



    public static void setAuthToken(Context context, String value) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_AUTH_TOKEN, value);
        editor.apply();
    }


    public static String getAuthToken(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_AUTH_TOKEN, "");

    }


    public static void setUserImage(Context context, String value) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_IMAGE_URL, value);
        editor.apply();
    }

    public static String getUserImageUrl(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_IMAGE_URL, "");

    }




    public static void setUserName(Context context, String value) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, value);
        editor.apply();
    }

    public static String getUserName(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME, "");

    }

    public static void setUserEmail(Context context, String value) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_EMAIL, value);
        editor.apply();
    }

    public static String getUserEmail(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_EMAIL, "");

    }

    public static void setUserLoggedIn(Context context, boolean value) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(USER_LOGGED_IN, value);
        editor.apply();
    }

    public static boolean isUserLoggedIn(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(USER_LOGGED_IN, false);

    }



    public static void setUserVerified(Context context, boolean value) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(USER_VERIFIED, value);
        editor.apply();
    }


    public static boolean isUserVerified(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(USER_VERIFIED, false);

    }

    public static void setLastSyncDate(Context context, String value) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LAST_SYNC_DATE, value);
        editor.apply();
    }

    public static String getLastSyncDate(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(LAST_SYNC_DATE, "");

    }

    public static void clearUserPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
