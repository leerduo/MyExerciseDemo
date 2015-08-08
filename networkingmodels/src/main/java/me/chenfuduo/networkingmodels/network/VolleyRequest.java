package me.chenfuduo.networkingmodels.network;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import me.chenfuduo.networkingmodels.application.MyApplication;

/**
 * Created by Administrator on 2015/8/8.
 * 二次封装的请求方式
 */
public class VolleyRequest {
    public static StringRequest stringRequest;
    public static Context context;

    public static void requestGet(Context context, String url, String tag, VolleyInterface volleyInterface) {
        MyApplication.getMyRequestQueue().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET, url,
                volleyInterface.loadingListener(), volleyInterface.errorListener());
        stringRequest.setTag(tag);
        MyApplication.getMyRequestQueue().add(stringRequest);
        MyApplication.getMyRequestQueue().start();
    }

    public static void requestPost(Context context, String url, String tag,
                                   final Map<String, String> params, VolleyInterface volleyInterface) {
        MyApplication.getMyRequestQueue().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.POST, url, volleyInterface.loadingListener(),
                volleyInterface.errorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        MyApplication.getMyRequestQueue().add(stringRequest);
        MyApplication.getMyRequestQueue().start();
    }

}
