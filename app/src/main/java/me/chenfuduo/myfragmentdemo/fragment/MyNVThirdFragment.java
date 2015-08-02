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
public class MyNVThirdFragment extends Fragment {

    public static MyNVThirdFragment newInstance(){
        MyNVThirdFragment myNVThirdFragment = new MyNVThirdFragment();
        return myNVThirdFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());

        tv.setText("MyNVThirdFragment");

        tv.setTextSize(20);

        tv.setGravity(Gravity.CENTER);

        return tv;
    }


}
