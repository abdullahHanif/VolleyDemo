package com.abdullahhanif.volleydemo.Helper;

/**
 * Created by Abdullah
 */

public interface VolleyResponseListener {

    void onError(int StatusCode , String responseMessage);

    void onResponse(int StatusCode , String stringObject);
}
