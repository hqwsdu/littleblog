package com.example.zt.feeling.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.zt.feeling.Model.ImageFile;
import com.example.zt.feeling.R;
import com.example.zt.feeling.tools.URL;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ImageAdapter extends BaseAdapter{
    private List<ImageFile> list;
    private Context context;


    public ImageAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @Override

    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public void addList(List<ImageFile> list1)
    {
        list.addAll(list1);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        Log.i("TAG",list.get(i).toString());
        if(view==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.writeimage,null);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) view.findViewById(R.id.write_image);
            view.setTag(viewHolder);

        }else
        {
            viewHolder= (ViewHolder) view.getTag();



        }
        ImageFile imageFile=list.get(i);
        Log.d("SSS",imageFile.toString());
        x.image().bind(viewHolder.imageView, imageFile.getFile_address());

        return view;
    }
    static class ViewHolder{
        private ImageView imageView;
    }

}
