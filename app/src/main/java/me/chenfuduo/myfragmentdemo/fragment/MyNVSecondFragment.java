package me.chenfuduo.myfragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/8/2.
 *
 * 测试NavigationView用的Fragment
 */
public class MyNVSecondFragment extends Fragment {

    public static MyNVSecondFragment newInstance(){
        MyNVSecondFragment myNVSecondFragment = new MyNVSecondFragment();
        return myNVSecondFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());

        tv.setText("MyNVSecondFragment");

        tv.setTextSize(20);

        tv.setGravity(Gravity.CENTER);

        return tv;
    }


}
