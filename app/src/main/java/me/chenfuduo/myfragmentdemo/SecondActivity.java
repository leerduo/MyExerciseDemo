package me.chenfuduo.myfragmentdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import me.chenfuduo.myfragmentdemo.adapter.SmartFragmentPagerAdapter;
import me.chenfuduo.myfragmentdemo.utils.LockableViewPager;
import me.chenfuduo.myfragmentdemo.utils.RotationPageTransformer;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private LockableViewPager viewPager;

    //第一次的Adapter
   // private MyPagerAdapter pagerAdapter;

    public SmartFragmentPagerAdapter pagerAdapter;

    private static final String TAG = SecondActivity.class.getSimpleName();
    public static final String POSITION = "POSITION";

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        viewPager = (LockableViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new SmartFragmentPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(pagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }

        viewPager.setOffscreenPageLimit(3);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(12);
        viewPager.setSwipeable(false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Log.d(TAG, "onPageScrolled ");
            }

            @Override
            public void onPageSelected(int position) {
               /* Toast.makeText(SecondActivity.this,
                        "Selected page position " + position, Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //  Log.d(TAG, "onPageScrollStateChanged " + state);
            }
        });


       viewPager.setPageTransformer(true,new RotationPageTransformer(165));

        Button btn = (Button) findViewById(R.id.btnReplace);
        btn.setOnClickListener(this);


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, viewPager.getCurrentItem());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume ");

    }

    @Override
    public void onClick(View v) {
       // Fragment fragment = pagerAdapter.getRegisteredFragment(0);
        Fragment fragment = pagerAdapter.getRegisteredFragment(viewPager.getCurrentItem());
        if (fragment != null && fragment.isVisible()) {
            fragment.getView().setBackgroundResource(R.drawable.entrance1);
        }
    }



}
