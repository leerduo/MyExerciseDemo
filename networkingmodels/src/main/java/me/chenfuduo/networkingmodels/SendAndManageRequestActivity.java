package me.chenfuduo.networkingmodels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.chenfuduo.networkingmodels.adapter.MySinaWeiboBaseAdapter;
import me.chenfuduo.networkingmodels.bean.MyWeibo;
import me.chenfuduo.networkingmodels.utils.HttpCallbackListener;
import me.chenfuduo.networkingmodels.utils.HttpUtil;

public class SendAndManageRequestActivity extends AppCompatActivity {

    private static final String TAG = SendAndManageRequestActivity.class.getSimpleName();

    private ListView listView;

    private List<MyWeibo> weiboList = new ArrayList<>();

    private Button btnSendHttpRequest;
    private Button btnSendJsonRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_and_manage_request);
        listView = (ListView) findViewById(R.id.listView);
        btnSendHttpRequest = (Button) findViewById(R.id.btnSendHttp);
        btnSendJsonRequest = (Button) findViewById(R.id.btnSendJsonRequest);
        initData();

    }

    private void initData() {
        HttpUtil.sendHttpRequest("https://api.weibo.com/2/statuses/public_timeline.json?source=1710941608", new HttpCallbackListener() {
            @Override
            public void onSuccess(final String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("statuses");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = (JSONObject) jsonArray.get(i);
                        String text = jo.getString("text");

                        String avatarUrl = jo.getJSONObject("user").getString("avatar_hd");

                        String name = jo.getJSONObject("user").getString("screen_name");
                        Log.e(TAG, text + "\n" + avatarUrl + "\n" + name);

                        weiboList.add(new MyWeibo(text, avatarUrl, name));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(new MySinaWeiboBaseAdapter(weiboList,
                                SendAndManageRequestActivity.this));
                    }
                });

            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "onError " + e.getMessage());
            }
        });


    }

    //https://api.weibo.com/2/users/show.json?source=1710941608&uid=2338582935
    public void sendHttpRequest(View view) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("source", "1710941608");
        //params.put("uid", "2338582935");
        client.get("https://api.weibo.com/2/statuses/public_timeline.json", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(TAG, "onFailure statusCode:" + statusCode + "\n" + "responseString:" + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.e(TAG, "onSuccess responseString:" + responseString);
            }
        });
    }

    public void sendJsonRequest(View view) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("source", "1710941608");
        //params.put("uid", "2338582935");
        client.get("https://api.weibo.com/2/statuses/public_timeline.json", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.e(TAG, "onSuccess response:" + response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e(TAG, "onFailure responseString:" + responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e(TAG, "onFailure errorResponse:" + errorResponse);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_and_manage_request, menu);
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
