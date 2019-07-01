package com.example.zt.feeling.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.alibaba.fastjson.JSON;
import com.example.zt.feeling.Adapter.MyBlogAdapter;
import com.example.zt.feeling.Model.BlogUi;
import com.example.zt.feeling.Model.BlogUser;
import com.example.zt.feeling.MyApp;
import com.example.zt.feeling.R;
import com.example.zt.feeling.tools.URL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBlogFragment extends Fragment {

    private ListView listView;
    private MyBlogAdapter blogAdapter;
    private List<String> list=new ArrayList<>();
    private View headView;
    private View footView;

    private MyApp myApp;
    private MyBlogAdapter myBlogAdapter;


    public MyBlogFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       headView=inflater.inflate(R.layout.myblog_headview,null);
        footView=inflater.inflate(R.layout.listheadview,null);
        return inflater.inflate(R.layout.fragment_my_blog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView= (ListView) view.findViewById(R.id.myBlog_ListView);
        blogAdapter=new MyBlogAdapter(getActivity());
        myApp= (MyApp) getActivity().getApplication();
        BlogUser blogUser=myApp.getBlogUser();

        myBlogAdapter=new MyBlogAdapter(getActivity());
        listView.setAdapter(myBlogAdapter);
        RequestParams requestParams=new RequestParams(URL.URL_MYBLOG);
        requestParams.addBodyParameter("data",String.valueOf(blogUser.getBloguser_id()));
        x.http().post(requestParams, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<BlogUi> list= JSON.parseArray(result,BlogUi.class);
                myBlogAdapter.addAll(list);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });


    }
}
