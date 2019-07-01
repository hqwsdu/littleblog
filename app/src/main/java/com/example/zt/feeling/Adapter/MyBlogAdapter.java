package com.example.zt.feeling.Adapter;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zt.feeling.Model.Blog;
import com.example.zt.feeling.Model.BlogUi;
import com.example.zt.feeling.R;
import com.example.zt.feeling.tools.URL;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2016/5/2.
 */
public class MyBlogAdapter extends BaseAdapter{
    private List<BlogUi> list;
    private Context context;

    public MyBlogAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void addAll(List<BlogUi> list1)
    {
        list.clear();
        list.addAll(list1);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.blogitem_final,null);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.blogitem_headImageView);
            viewHolder.textView_name= (TextView) convertView.findViewById(R.id.blogitem_nickname);
            viewHolder.textView_date= (TextView) convertView.findViewById(R.id.blogitem_date);
            viewHolder.textView_content= (TextView) convertView.findViewById(R.id.blogitem_content);
            viewHolder.gridView= (GridView) convertView.findViewById(R.id.blogitem_gridview);
            viewHolder.textView_state= (TextView) convertView.findViewById(R.id.blogitem_state);
            convertView.setTag(viewHolder);


        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();




        }
        final BlogUi blog=list.get(position);
        //这里就已经是乱码
        Log.v("加载主页的adpter里面", "用户名："+blog.getBloguser_nickname());
        Log.v("加载主页的adpter里面", "内容："+blog.getBlog_content());
        viewHolder.textView_content.setText(blog.getBlog_content());
        viewHolder.textView_date.setText(blog.getBlog_date());
        viewHolder.textView_name.setText(blog.getBloguser_nickname());
        viewHolder.textView_state.setText(blog.getBlogstate());
        String url=URL.URL_HEADIMAGE+String.valueOf(blog.getBloguser_id())+".png";
        Log.v("加载主页的adpter里面", "图片的地址："+url);
        x.image().bind(viewHolder.imageView,url);
        final ImageAdapter imageAdapter=new ImageAdapter(context);
        viewHolder.gridView.setAdapter(imageAdapter);
        imageAdapter.addList(blog.getList());

        return convertView;
    }
    static  class ViewHolder
    {
        ImageView imageView;
        TextView textView_name,textView_date,textView_state,textView_content;
        GridView gridView;


    }
}
