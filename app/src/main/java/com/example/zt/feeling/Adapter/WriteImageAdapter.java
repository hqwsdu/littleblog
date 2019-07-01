package com.example.zt.feeling.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.zt.feeling.R;

import java.util.List;

/**
 *
 */
public class WriteImageAdapter extends BaseAdapter {
    private List<Bitmap> list;
    private Context context;

    public WriteImageAdapter(Context context, List<Bitmap> bitmap) {
        this.context = context;
        this.list = bitmap;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.writeimage,null);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.write_image);
            convertView.setTag(viewHolder);
            Log.i("TAG1",position+"");
        }else
        {
            viewHolder= (ViewHolder) convertView.getTag();

            Log.i("TAG",position+"");
        }
        viewHolder.imageView.setImageBitmap(list.get(position));
        return convertView;
    }
    static class ViewHolder{
        private ImageView imageView;
    }

}
