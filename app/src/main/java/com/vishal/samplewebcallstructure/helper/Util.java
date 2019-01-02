package com.vishal.samplewebcallstructure.helper;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.vishal.samplewebcallstructure.R;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import timber.log.Timber;

public class Util {




    /**
     * @param activity : Contain activity
     * @return boolean : True or false
     * @description : Check data connection is on or not
     */

    public static boolean isInternetOn(Activity activity) {


        ConnectivityManager connec;
        connec = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (connec != null) {
            activeNetwork = connec.getActiveNetworkInfo();
        }
        // Check for network connections
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                if (activeNetwork.getState() == NetworkInfo.State.CONNECTED) {
                    // if connected with internet


                    return true;
                } else // connected to the mobile provider's data plan
                    return activeNetwork.getState() == NetworkInfo.State.CONNECTING;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                if (activeNetwork.getState() == NetworkInfo.State.CONNECTED) {
                    // connected to the mobile provider's data plan


                    return true;
                } else // connected to the mobile provider's data plan
                    return activeNetwork.getState() == NetworkInfo.State.CONNECTING;


            }
        } else {
//            buildDialog(activity);
            //  Toast.makeText(activity, "Internet Not Available ", Toast.LENGTH_LONG).show();

            return false;
        }


        return false;
    }





    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }



    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }



}
