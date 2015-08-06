package me.chenfuduo.networkingmodels;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


//08-05 06:48:47.928  29890-29920/me.chenfuduo.networkingmodels D/skia﹕ --- decoder->decode returned false

public class MyAsyncTaskActivity extends AppCompatActivity {

    private static final String TAG = MyAsyncTaskActivity.class.getSimpleName();

    private static final String IMAGE_URL = "http://1.avatarinfo.sinaapp.com/pic/3.png";

    private ProgressBar progressBar;

    private ProgressBar mLoadingPb;

    private ProgressDialog progressDialog;

    //  private MenuItem miActionProgressItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_async_task);

        //   AsyncTask
        ImageView imageView = (ImageView) findViewById(R.id.iv);
        progressBar = (ProgressBar) findViewById(R.id.pb);

        mLoadingPb = new ProgressBar(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提示信息");
        progressDialog.setMessage("正在下载，请稍后...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        new MyAsyncTask(imageView).execute(IMAGE_URL);

    }


    private class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

        ImageView imageView;

        public MyAsyncTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "onPreExecute ");
            progressBar.setVisibility(ProgressBar.VISIBLE);
            progressDialog.show();
            // miActionProgressItem.setVisible(true);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Log.e(TAG, "doInBackground  params:" + params[0]);

            Bitmap bitmap = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream inputStream = null;
            HttpURLConnection conn = null;
            try {
                URL imageUrl = new URL(params[0]);
                conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();
                inputStream = conn.getInputStream();
                long fileLength = conn.getContentLength();
                int len;
                byte[] data = new byte[1024];
                int totalLength = 0;
                while ((len = inputStream.read(data)) != -1) {
                    totalLength += len;
                    int value = (int) ((totalLength / (float) fileLength) * 100);
                    publishProgress(value);
                    baos.write(data, 0, len);
                }
                byte[] bytes = baos.toByteArray();
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (baos != null) {
                    try {
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (conn != null) {
                    conn.disconnect();
                }

            }
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.e(TAG, "onProgressUpdate values:" + values[0]);
            progressBar.setProgress(values[0]);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Log.e(TAG, "onPostExecute ");
            imageView.setImageBitmap(bitmap);
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            // miActionProgressItem.setVisible(false);
            progressDialog.dismiss();
        }
    }

    /**
     * 初始版本，后来在下载网络图片加上进度条，此方法不再用
     * 参考代码
     *
     * @param address
     * @return
     */
    private Bitmap downloadBitmap(String address) {
        Bitmap bitmap = null;
        InputStream in = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(address);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            in = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                    conn.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }


    /**
     * 参考代码
     *
     * @param is
     * @return
     * @throws IOException
     */
    //定义一个根据图片url获取InputStream的方法
    private byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; // 用数据装
        int len;
        while ((len = is.read(buffer)) != -1) {
            outstream.write(buffer, 0, len);
        }
        outstream.close();
        // 关闭流一定要记得。
        return outstream.toByteArray();
    }


   /* @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        miActionProgressItem = menu.findItem(R.id.miActionProgress);
        ProgressBar v = (ProgressBar) MenuItemCompat.getActionView(miActionProgressItem);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_async_task, menu);
        return true;
    }
*/

}
