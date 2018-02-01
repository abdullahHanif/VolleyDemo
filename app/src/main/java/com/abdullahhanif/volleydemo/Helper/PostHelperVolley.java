package com.abdullahhanif.volleydemo.Helper;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Abdullah
 */

public class PostHelperVolley {

    private  VolleyResponseListener VolleyResponseListener;
    int mStatusCode;

    /**
     * This Constructor "PostHelperVolley()" Provides Basic data from Activtiy to class to perform a network call
     */
    public PostHelperVolley(VolleyResponseListener InterfaceToListenCallbackOnResponse,
                            String networkUrl,
                            String Email,
                            String Password) {
        this.VolleyResponseListener = InterfaceToListenCallbackOnResponse;

        sendLoginRequest( networkUrl , Email, Password);
    }

    private void sendLoginRequest(String Url, final String Email, final String Password) {

        StringRequest LoginRequest = new StringRequest(
                Request.Method.POST /*Post Parameter define resquest type*/,
                Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LoginResponse ", response + "");

                        //Call Back Interface Implementation will get Notified when Volley will done,
                        // In this Case our Class LoginActivity
                        VolleyResponseListener.onResponse(mStatusCode,response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("LoginResponse Error: ", error.getMessage());

                        //Call Back Interface Implementation will get Notified when Volley will done
                        VolleyResponseListener.onError(mStatusCode, error + "");

                        error.printStackTrace();
                    }
                })
        {
            @Override
            public byte[] getBody() throws AuthFailureError {
                //Here the Json body will get Prepared and will be sent to Server in Post Request Body
                HashMap<String, String> parameters = new HashMap<String, String>();
                parameters.put("Email", Email);
                parameters.put("Password", Password);

                return new JSONObject(parameters).toString().getBytes();
            }

            @Override
            //Defines that my application Type is Json
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            //Grabbing the status code
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                // Like 200 ok 404 not found
                mStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }

        };
        //This singleton Maintain Only one queue in app, and mandatory to add requestObject in this queue.
        VolleySingleton.getInstance().getRequestQueue().add(LoginRequest);

    }
}


