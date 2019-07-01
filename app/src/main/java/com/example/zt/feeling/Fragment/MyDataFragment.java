package com.example.zt.feeling.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zt.feeling.Model.BlogUser;
import com.example.zt.feeling.MyApp;
import com.example.zt.feeling.R;
import com.example.zt.feeling.tools.URL;

import org.xutils.x;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyDataFragment extends Fragment {
    private TextView textView_date,textView_phone,textView_data;
    private MyApp myApp;
    private ImageView imageView;


    public MyDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_data, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView_date= (TextView) view.findViewById(R.id.mydata_date);
        textView_phone= (TextView) view.findViewById(R.id.mydata_phone);
        textView_data= (TextView) view.findViewById(R.id.mydata_data);
        imageView= (ImageView) view.findViewById(R.id.mydata_imageview);

        myApp= (MyApp) getActivity().getApplication();
        BlogUser blogUser=myApp.getBlogUser();

        textView_date.setText("注册时间 ："+blogUser.getBloguser_date());
        textView_phone.setText("用户电话："+blogUser.getBloguser_phone());
        textView_data.setText(blogUser.getBloguser_data());
        String url= URL.URL_HEADIMAGE+blogUser.getBloguser_id()+".png";
        x.image().bind(imageView,url);
    }
}
