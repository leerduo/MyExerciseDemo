package me.chenfuduo.myfragmentdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.chenfuduo.myfragmentdemo.fragment.FirstFragment;
import me.chenfuduo.myfragmentdemo.fragment.SecondFragment;

/**
 * Created by Administrator on 2015/7/30.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 3;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FirstFragment.newInstance(0, "Page 1");
            case 1:
                return FirstFragment.newInstance(1, "Page 2");
            case 2:
                return SecondFragment.newInstance(2, "Page 3");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}
