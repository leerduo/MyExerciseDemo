package me.chenfuduo.networkingmodels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.chenfuduo.networkingmodels.application.MyApplication;
import me.chenfuduo.networkingmodels.network.VolleyInterface;
import me.chenfuduo.networkingmodels.network.VolleyRequest;

public class MyVolleyBasicUsageActivity extends AppCompatActivity {

    private TextView tv;

    private static final String URL_GET = "https://api.weibo.com/2/statuses/public_timeline.json?source=1710941608";

    private static final String URL_POST = "https://api.weibo.com/2/statuses/repost.json?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_volley_basic_usage);
        tv = (TextView) findViewById(R.id.tv);
    }

    /**
     * 发送StringRequest请求
     * 默认的请求方式是get
     *
     * @param view
     */
    public void sendStringRequest(View view) {

        StringRequest stringRequest = new StringRequest(URL_GET, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tv.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tv.setText(volleyError.getMessage());
            }
        });
        stringRequest.setTag("MYSTRINGREQUEST");
        MyApplication.getMyRequestQueue().add(stringRequest);
    }


    /**
     * 发送JsonObjectRequest请求
     * 默认的请求方式是get
     *
     * @param view
     */
    public void sendJsonObjectRequest(View view) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL_GET, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                tv.setText(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tv.setText(volleyError.toString());
            }
        });
        jsonObjectRequest.setTag("MYSTRINGREQUEST");
        MyApplication.getMyRequestQueue().add(jsonObjectRequest);

    }


    /**
     * StringRequest
     * post请求
     *
     * @param view
     */
    public void sendPostStringRequest(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tv.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tv.setText(volleyError.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", "3873256514023552");
                params.put("access_token", "2.00bP9QYC93WiBCffdf5766d0T3YFrB");
                return params;
            }
        };
        stringRequest.setTag("MYSTRINGREQUEST");
        MyApplication.getMyRequestQueue().add(stringRequest);
    }

    /**
     * JsonObjectRequest
     * post请求
     *
     * @param view
     */
    public void sendPostJsonObjectRequest(View view) {
        Map<String, String> params = new HashMap<>();
        params.put("id", "3873256514023552");
        params.put("access_token", "2.00bP9QYC93WiBCffdf5766d0T3YFrB");
        JSONObject jsonObject1 = new JSONObject(params);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_POST,
                jsonObject1, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                tv.setText(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tv.setText(volleyError.toString());
            }
        });
        jsonObjectRequest.setTag("MYSTRINGREQUEST");
        MyApplication.getMyRequestQueue().add(jsonObjectRequest);
    }

    /**
     * 二次封装的StringRequest的get请求
     *
     * @param view
     */
    public void sendCustomStringRequestGet(View view) {
        VolleyRequest.requestGet(this, URL_GET, "MYSTRINGREQUEST", new VolleyInterface(this,
                VolleyInterface.listener, VolleyInterface.errorListener) {
            @Override
            public void onMySucess(String result) {
                tv.setText(result);
            }

            @Override
            public void onError(VolleyError error) {
                tv.setText(error.toString());
            }
        });
    }

    /**
     * 二次封装的StringRequest的post请求
     *
     * @param view
     */
    public void sendCustomStringRequestPost(View view) {
        Map<String, String> params = new HashMap();
        params.put("id", "3873256514023552");
        params.put("access_token", "2.00bP9QYC93WiBCffdf5766d0T3YFrB");
        VolleyRequest.requestPost(this, URL_POST, "MYSTRINGREQUEST", params, new VolleyInterface(this,
                VolleyInterface.listener, VolleyInterface.errorListener) {
            @Override
            public void onMySucess(String result) {
                tv.setText(result);
            }

            @Override
            public void onError(VolleyError error) {
                tv.setText(error.toString());
            }
        });
    }

    /**
     * 和Activity生命周期联调
     */
    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getMyRequestQueue().cancelAll("MYSTRINGREQUEST");
    }


    /**
     * 不页面剩余范围不大了 跳转到其他的Activity进行ImageRequest演示
     *
     * @param view
     */
    public void clickToImageLoaderActivity(View view) {
        Intent intent = new Intent(MyVolleyBasicUsageActivity.this, MyImageLoaderActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_volley_basic_usage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
