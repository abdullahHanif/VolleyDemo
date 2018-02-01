package com.abdullahhanif.volleydemo.Helper;

import android.app.Application;
import android.content.Context;

/**
 * Created by Abdullah
 */

public class CustomApplication extends Application{

    /*added in Manifest Also that this class is child of application in Application Tag
    *   android:name=".Helper.CustomApplication"
    * */
    public static final String TAG = CustomApplication.class.getSimpleName();
    private static CustomApplication mInstance;

    public static CustomApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
