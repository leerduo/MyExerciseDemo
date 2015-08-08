package me.chenfuduo.networkingmodels.network;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2015/8/8.
 * <p/>
 * 负责接口回调
 */
public abstract class VolleyInterface {
    private Context context;
    public static Response.Listener<String> listener;
    public static Response.ErrorListener errorListener;

    public VolleyInterface(Context context,
                           Response.Listener<String> listener,
                           Response.ErrorListener errorListener) {
        this.context = context;
        this.listener = listener;
        this.errorListener = errorListener;
    }

    public abstract void onMySucess(String result);

    public abstract void onError(VolleyError error);

    public Response.Listener<String> loadingListener() {
        listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                onMySucess(s);
            }
        };
        return listener;
    }

    public Response.ErrorListener errorListener() {
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onError(volleyError);
            }
        };
        return errorListener;
    }

}
