package me.chenfuduo.myfragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import me.chenfuduo.myfragmentdemo.R;

/**
 * Created by Administrator on 2015/7/31.
 */
public class DetailFragment extends Fragment {

    private TextView tv;

    public static final String ARG_POSITION = "position";


    public static DetailFragment newInstance(int position) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        detailFragment.setArguments(args);
        return detailFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        tv = (TextView) view.findViewById(R.id.tvDetail);
        tv.setText("");

        int position = getArguments().getInt(ARG_POSITION);

        updateTextView(position);

        return view;
    }

    public void updateTextView(int position) {
        switch (position) {
            case 0:
                tv.setText("胡一菲");
                break;
            case 1:
                tv.setText("曾小贤");
                break;
            case 2:
                tv.setText("陈美嘉");
                break;
            case 3:
                tv.setText("陆展博");
                break;
            case 4:
                tv.setText("吕子乔");
                break;
            case 5:
                tv.setText("唐悠悠");
                break;
            case 6:
                tv.setText("关谷神奇");
                break;
            case 7:
                tv.setText("秦玉墨");
                break;
            case 8:
                tv.setText("林宛瑜");
                break;
            case 9:
                tv.setText("木婉清");
                break;

            case 10:
                tv.setText("陈福多");
                break;

        }
    }



  /*  @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(getActivity(), "设置", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }*/

}
