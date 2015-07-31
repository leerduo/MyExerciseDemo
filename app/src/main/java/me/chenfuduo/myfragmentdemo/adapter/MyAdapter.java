package me.chenfuduo.myfragmentdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.chenfuduo.myfragmentdemo.R;
import me.chenfuduo.myfragmentdemo.bean.Person;

/**
 * Created by Administrator on 2015/7/30.
 */
public class MyAdapter extends BaseAdapter {


    private Context context;

    private List<Person> personList;

    public MyAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view;
        if (convertView == null) {
            view = View.inflate(context,R.layout.item,null);
            holder = new ViewHolder();
            holder.icon = (ImageView) view.findViewById(R.id.icon);
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        }else{
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.icon.setImageResource(personList.get(position).getIcon());
        holder.name.setText(personList.get(position).getName());

        return view;

    }

    static class ViewHolder {
        ImageView icon;
        TextView name;
    }

}
