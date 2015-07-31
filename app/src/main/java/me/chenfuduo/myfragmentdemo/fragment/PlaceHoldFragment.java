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
 * Created by Administrator on 2015/7/31.
 */
public class PlaceHoldFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());

        tv.setText("备胎Fragment");
        tv.setGravity(Gravity.CENTER);

        tv.setTextSize(30);

        return tv;
    }
}
