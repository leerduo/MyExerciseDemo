package me.chenfuduo.myfragmentdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.chenfuduo.myfragmentdemo.R;
import me.chenfuduo.myfragmentdemo.fragment.FirstFragment;
import me.chenfuduo.myfragmentdemo.fragment.SecondFragment;

/**
 * Created by Administrator on 2015/7/31.
 */
public class SmartFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    private static final String TAG = SmartFragmentPagerAdapter.class.getSimpleName();

    private static int NUM_ITEMS = 3;

    private Context context;

    private int[] imageResId = {R.drawable.avatar_enterprise_vip,
            R.drawable.avatar_grassroot,
            R.drawable.avatar_vip};

    public SmartFragmentPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FirstFragment.newInstance(0,"Page # 1");
            case 1:
                return FirstFragment.newInstance(1,"Page # 2");
            case 2:
                return SecondFragment.newInstance(2,"page # 3");
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.93f;
    }

    public View getTabView(int position){
        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        tv.setText(getPageTitle(position));
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(imageResId[position]);
        return view;
    }

}
