package me.chenfuduo.networkingmodels.network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Administrator on 2015/8/8.
 */
public class BitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> lruCache;
    private int max = 10 * 1024 * 1024;

    public BitmapCache() {
        lruCache = new LruCache<String, Bitmap>(max) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return lruCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        lruCache.put(s, bitmap);
    }
}
