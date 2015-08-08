package me.chenfuduo.networkingmodels;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;

import me.chenfuduo.networkingmodels.application.MyApplication;
import me.chenfuduo.networkingmodels.network.BitmapCache;
import me.chenfuduo.networkingmodels.utils.MyToast;

public class MyImageLoaderActivity extends AppCompatActivity {

    private ImageView iv;
    private NetworkImageView niv;

    private static final String IMAGE_URL = "http://1.avatarinfo.sinaapp.com/pic/3.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_image_loader);
        iv = (ImageView) findViewById(R.id.iv);
        niv = (NetworkImageView) findViewById(R.id.niv);
    }


    /**
     * ImageRequest请求图片
     *
     * @param view
     */
    public void sendImageRequest(View view) {
        //int maxWidth, int maxHeight这两个参数为0的话，表示加载图片原始尺寸
        ImageRequest imageRequest = new ImageRequest(IMAGE_URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                iv.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                MyToast.showToast(MyImageLoaderActivity.this, "加载失败:" + volleyError.toString());
                iv.setImageResource(R.mipmap.ic_launcher);
            }
        });
        imageRequest.setTag("IMAGEREQUEST");
        MyApplication.getMyRequestQueue().add(imageRequest);
    }

    /**
     * ImageLoader加载图片
     *
     * @param view
     */
    public void sendImageLoader(View view) {
        ImageLoader imageLoader = new ImageLoader(MyApplication.getMyRequestQueue(), new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(iv, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(IMAGE_URL, listener);
    }


    /**
     * NetworkImageView＋ImageLoader加载图片
     *
     * @param view
     */
    public void sendNIVWithImageLoader(View view) {
        ImageLoader imageLoader = new ImageLoader(MyApplication.getMyRequestQueue(), new BitmapCache());
        niv.setDefaultImageResId(R.mipmap.ic_launcher);
        niv.setErrorImageResId(R.mipmap.ic_launcher);
        niv.setImageUrl(IMAGE_URL, imageLoader);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getMyRequestQueue().cancelAll("IMAGEREQUEST");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_image_loader, menu);
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
