package me.chenfuduo.networkingmodels.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by Administrator on 2015/8/7.
 */
public class MyApplication extends Application {

    public static RequestQueue myRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        myRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getMyRequestQueue() {
        return myRequestQueue;
    }

}
