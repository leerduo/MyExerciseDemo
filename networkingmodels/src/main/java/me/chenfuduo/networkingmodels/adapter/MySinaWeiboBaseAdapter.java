package me.chenfuduo.networkingmodels.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.chenfuduo.networkingmodels.R;
import me.chenfuduo.networkingmodels.bean.MyWeibo;


/**
 * Created by Administrator on 2015/8/3.
 */
public class MySinaWeiboBaseAdapter extends BaseAdapter {

    private List<MyWeibo> weiboList;

    private Context context;

    public MySinaWeiboBaseAdapter(List<MyWeibo> weiboList, Context context) {
        this.weiboList = weiboList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return weiboList.size();
    }

    @Override
    public Object getItem(int position) {
        return weiboList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view;
        if (convertView == null){
            view = View.inflate(context,R.layout.item,null);
            holder = new ViewHolder();
            holder.icon = (ImageView) view.findViewById(R.id.icon);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.content = (TextView) view.findViewById(R.id.content);
            view.setTag(holder);
        }else{
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        //设置头像
        String avatarUrl = weiboList.get(position).getAvatarUrl();

        Picasso.with(context).load(avatarUrl).into(holder.icon);

        //设置用户名
        holder.name.setText(weiboList.get(position).getName());
        //设置微博内容
        holder.content.setText(weiboList.get(position).getText());

        return view;
    }


    static class ViewHolder{
        ImageView icon;
        TextView name;
        TextView content;
    }

}
