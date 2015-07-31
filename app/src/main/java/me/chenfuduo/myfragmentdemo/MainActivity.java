package me.chenfuduo.myfragmentdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import me.chenfuduo.myfragmentdemo.fragment.FooFragment;

public class MainActivity extends AppCompatActivity {

    private FooFragment fooFragment;

    private static final String TAG = "FOOFRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //第一次练习的代码
        /**
        //第一步：getSupportFragmentManager()得到一个FragmentManager
        //对象，通过FragmentManager对象的beginTransaction()方法得到
        //FragmentTransaction，接着FragmentTransaction便可以去操作Fragment了
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        //将container的内容更换为新的Fragment，或者使用add(id,fragment)
        fragmentTransaction.replace(R.id.your_placeholder, new FooFragment());
        //fragmentTransaction.add(R.id.your_placeholder,new FooFragment());
        //提交
        fragmentTransaction.commit();
        */

        //查找Fragment实例的三种方式

        //第一种方式： Lookup a fragment by calling findFragmentById on the FragmentManager
        //适用于Fragment以xml的形式添加到Fragment中的情况

        /**
        FragmentManager fragmentManager =
                getSupportFragmentManager();

        fooFragment = (FooFragment) fragmentManager.findFragmentById(R.id.fooFragment);

        if (savedInstanceState == null){
            fooFragment = new FooFragment();
            fragmentManager.beginTransaction().add(R.id.fooFragment,fooFragment).commit();
        }
        */


        //第二种方式：Lookup a fragment by calling findFragmentByTag on the FragmentManager
        //If the fragment was dynamically added at runtime within an activity
        // then we can lookup this fragment by tag by calling findFragmentByTag on the FragmentManager
        /**
        FragmentManager fragmentManager = getSupportFragmentManager();
        fooFragment = (FooFragment) fragmentManager.findFragmentByTag(TAG);

        if (savedInstanceState == null){
            fooFragment = new FooFragment();
            fragmentManager.beginTransaction().replace(R.id.your_placeholder,fooFragment,TAG);
        }
         */



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
