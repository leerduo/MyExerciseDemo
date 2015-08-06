package me.chenfuduo.mylayoutparse;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dalvik.system.PathClassLoader;

public class MyService extends Service {

    private static final String TAG = MyService.class.getSimpleName();

    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //抓取资源文件
            try {
                getLayoutFromActivity();
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.postDelayed(runnable, 10000);
        }
    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(runnable);
        return super.onStartCommand(intent, flags, startId);
        //抓取正在运行的程序的资源文件，循环抓取
    }

    private void getLayoutFromActivity() {
        ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfos = am.getRunningTasks(5);
        for (ActivityManager.RunningTaskInfo taskInfo : taskInfos) {
            ComponentName topActivity = taskInfo.topActivity;//获取每一个程序的上层Activity
            String packageName = topActivity.getPackageName();
            Context qqContext;
            try {
                qqContext = this.createPackageContext(packageName,
                        Context.CONTEXT_IGNORE_SECURITY | CONTEXT_INCLUDE_CODE);
                //类加载器
                PathClassLoader pathClassLoader = new PathClassLoader(qqContext.getPackageResourcePath(),
                        ClassLoader.getSystemClassLoader());
                //加载R文件
                Class<?> forName;
                forName = Class.forName(packageName + ".R$layout", true, pathClassLoader);
                Field[] fields = forName.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    if (i < 20 && !topActivity.getClassName().contains("launcher")) {
                        //每一个布局文件
                        Field field = fields[i];
                        Log.e(TAG, "layoutName:" + field.getName());
                        XmlResourceParser xmlResourceParser;
                        int resource;
                        resource = field.getInt(R.layout.class);
                        //获取解析布局文件的核心类
                        xmlResourceParser = qqContext.getResources().getLayout(resource);
                        //解析xml
                        //文档类型
                        int type = xmlResourceParser.getEventType();
                        while (type != XmlResourceParser.END_DOCUMENT) {
                            //说明文档还没结束,需要进行一行行的解析
                            //进行正常的解析
                            String tagName;
                            List<String> layouts = new ArrayList<>();//判断双标记
                            List<String> views = new ArrayList<>();//判断单标记
                            switch (type) {
                                case XmlResourceParser.START_TAG:
                                    tagName = xmlResourceParser.getName();//开始标记
                                    if (tagName.contains("Layout")) {
                                        layouts.add(tagName);//双标记
                                    } else {
                                        views.add(tagName);//单标记
                                    }
                                    Log.e(TAG, "<" + tagName);
                                    //解析属性
                                    for (int j = 0; j < xmlResourceParser.getAttributeCount(); j++) {
                                        Log.e(TAG, xmlResourceParser.getAttributeName(j) + "="
                                                + xmlResourceParser.getAttributeValue(j));
                                    }
                                    break;
                                case XmlResourceParser.END_TAG:
                                    String endName = xmlResourceParser.getName();//结束标记
                                    if (endName.contains("Layout")) {
                                        if (layouts.contains(endName)) {
                                            //说明这是开启标记的回退标记
                                            layouts.remove(endName);
                                        } else {
                                            Log.e(TAG, "</" + endName + ">");
                                        }
                                    } else {
                                        //说明是单标记
                                        if (views.contains(endName)) {
                                            Log.e(TAG, "/>");
                                        } else {
                                            //do nothing
                                        }
                                    }

                                    break;
                                default:
                                    break;
                            }

                            //解析下一行
                            type = xmlResourceParser.next();
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
