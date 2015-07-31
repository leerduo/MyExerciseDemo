package me.chenfuduo.myfragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.chenfuduo.myfragmentdemo.R;

/**
 * Created by Administrator on 2015/7/30.
 */
public class FirstFragment extends Fragment {

    private String title;
    private int page;


    public static FirstFragment newInstance(int page,String title){
        FirstFragment firstFragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle",title);
        firstFragment.setArguments(args);
        return firstFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt",0);
        title = getArguments().getString("someTitle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(page + 1 + "---" + title);
        return view;
    }
}
