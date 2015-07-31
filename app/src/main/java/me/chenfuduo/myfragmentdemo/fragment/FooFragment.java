package me.chenfuduo.myfragmentdemo.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.chenfuduo.myfragmentdemo.R;
import me.chenfuduo.myfragmentdemo.adapter.MyAdapter;
import me.chenfuduo.myfragmentdemo.bean.Person;

/**
 * Created by Administrator on 2015/7/30.
 */
public class FooFragment extends Fragment {

    private MyAdapter adapter;

    private List<Person> personList;

    private OnMyItemSelectedListener listener;

    private static final String TAG = FooFragment.class.getSimpleName();

    /**
     * is called when a fragment is connected to an activity.
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "onAttach ");
        if (activity instanceof OnMyItemSelectedListener) {
            listener = (OnMyItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString() +
                    "must implement FootFragment.OnMyItemSelectedListener");
        }
    }


    /**
     * is called to do initial creation of the fragment.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.i(TAG, "Fragment onCreate ");

        Person p = getArguments().getParcelable("NEW_ADD");
        initData();
        personList.add(p);

        adapter = new MyAdapter(getActivity(), personList);
    }

    public static FooFragment newInstance(Person p) {
        Bundle args = new Bundle();
        args.putParcelable("NEW_ADD", p);
        FooFragment fragment = new FooFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public void toastHello(Context context, String params) {
        Toast.makeText(context, params, Toast.LENGTH_SHORT).show();
    }


    public interface OnMyItemSelectedListener {
        void onItemSelected(int position);
    }

    private void initData() {

        personList = new ArrayList<>();
        personList.add(new Person(R.mipmap.ic_launcher, "胡一菲"));
        personList.add(new Person(R.mipmap.ic_launcher, "曾小贤"));
        personList.add(new Person(R.mipmap.ic_launcher, "陈美嘉"));
        personList.add(new Person(R.mipmap.ic_launcher, "陆展博"));
        personList.add(new Person(R.mipmap.ic_launcher, "吕子乔"));
        personList.add(new Person(R.mipmap.ic_launcher, "唐悠悠"));
        personList.add(new Person(R.mipmap.ic_launcher, "关谷神奇"));
        personList.add(new Person(R.mipmap.ic_launcher, "秦玉墨"));
        personList.add(new Person(R.mipmap.ic_launcher, "林宛瑜"));
        personList.add(new Person(R.mipmap.ic_launcher, "木婉清"));
    }

    /**
     * is called by Android once the Fragment should inflate a view.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView ");
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.fragment_foo, container, false);
        // Setup handles to view objects here
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onItemSelected(position);
            }
        });

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(getActivity(),"设置",Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * is called when host activity has completed its onCreate() method.
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated ");
    }

    /**
     * is called once the fragment gets visible.
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart ");
    }

    /**
     * Allocate “expensive” resources such as registering for location, sensor updates, etc.
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume ");
    }

    /**
     * Release “expensive” resources. Commit any changes.
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause ");
    }

    /**
     * is called when fragment's view is being destroyed, but the fragment is still kept around.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView ");
    }

    /**
     * is called when fragment is no longer in use.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy ");
    }

    /**
     * is called when fragment is no longer connected to the activity.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach ");
    }
}