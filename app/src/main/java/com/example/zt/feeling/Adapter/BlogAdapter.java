package com.example.zt.feeling.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zt.feeling.R;
import com.example.zt.feeling.TestData.Test;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2016/5/2.
 */
public class BlogAdapter extends BaseAdapter {


    public BlogAdapter(Context context) {
        this.context = context;

            this.list=new ArrayList<>();

    }


    private Context context;
    private List<Test> list;
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

    public void addAll(List<Test> list1)
    {
        list.addAll(list1);
    }

    @Override
    public int getItemViewType(int position) {
        if (list != null && position < list.size()) {
            return list.get(position).getType();
        }
        return super.getItemViewType(position);

    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type=list.get(position).getType();

        switch (type)
        {
            case 1:
                ViewHolder1 viewHolder1=null;
                if(convertView==null)
                {
                    viewHolder1=new ViewHolder1();
                    convertView=LayoutInflater.from(context).inflate(R.layout.blog_item,null);
                    viewHolder1.imageView= (ImageView) convertView.findViewById(R.id.blogitem1_imageView);
                    convertView.setTag(viewHolder1);
                }
                else
                {
                    viewHolder1= (ViewHolder1) convertView.getTag();
                    Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.text01);
                    bitmap= ThumbnailUtils.extractThumbnail(bitmap,200,200);
                    viewHolder1.imageView.setImageBitmap(bitmap);
                }



                break;
            case 2:
                ViewHolder2 viewHolder2=null;
                if(convertView==null)
                {
                    viewHolder2=new ViewHolder2();
                    convertView=LayoutInflater.from(context).inflate(R.layout.blog_item2,null);
                    viewHolder2.imge1= (ImageView) convertView.findViewById(R.id.blogitem2_image1);
                    viewHolder2.imge2= (ImageView) convertView.findViewById(R.id.blogitem2_image2);
                    convertView.setTag(viewHolder2);
                }
                else
                {
                    viewHolder2= (ViewHolder2) convertView.getTag();
                    Bitmap bitmap1= BitmapFactory.decodeResource(context.getResources(),R.drawable.text01);
                    bitmap1= ThumbnailUtils.extractThumbnail(bitmap1,200,200);
                    viewHolder2.imge1.setImageBitmap(bitmap1);
                    Bitmap bitmap2= BitmapFactory.decodeResource(context.getResources(),R.drawable.text02);
                    bitmap1= ThumbnailUtils.extractThumbnail(bitmap2,200,200);
                    viewHolder2.imge2.setImageBitmap(bitmap1);
                }
        }

        return convertView;
    }
    static  class  ViewHolder1
    {
        private ImageView imageView;

    }
    static class ViewHolder2
    {
        private ImageView imge1,imge2;

    }
    static  class ViewHolder3
    {
        private ImageView imge1,imge2;

    }

}
