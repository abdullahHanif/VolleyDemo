package com.abdullahhanif.volleydemo.Helper;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Abdullah
 */

public class VolleySingleton {

    // this volley singleton class is responsible for providing Single volley and request Queue object for application

    private static VolleySingleton mInstance = null;
    private RequestQueue requestQueue;


    private VolleySingleton() {
        requestQueue = Volley.newRequestQueue(CustomApplication.getAppContext());
    }

    public static VolleySingleton getInstance() {

        if (mInstance == null) {
            mInstance = new VolleySingleton();
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
