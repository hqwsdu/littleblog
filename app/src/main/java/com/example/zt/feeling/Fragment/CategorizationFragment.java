package com.example.zt.feeling.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.zt.feeling.Adapter.CateAdapter;
import com.example.zt.feeling.Adapter.MyBlogAdapter;
import com.example.zt.feeling.Model.BlogUi;
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
public class CategorizationFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private View headview;
    private GridView gridView;
    private MyBlogAdapter adapter;
    private List<String> list=new ArrayList<>();


    public CategorizationFragment() {
        // Required empty public constructor
        list.add("游戏");
        list.add("美女");
        list.add("明星");
        list.add("娱乐");
        list.add("音乐");
        list.add("国内");
        list.add("国外");
        list.add("军事");
        list.add("生物");
        list.add("互联网");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        headview=inflater.inflate(R.layout.myblog_headview,null);
        return inflater.inflate(R.layout.fragment_categorization, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CateAdapter cateAdapter=new CateAdapter(list,getActivity());
        listView= (ListView) view.findViewById(R.id.cate_listview);
        gridView= (GridView) headview.findViewById(R.id.head_view);
        adapter=new MyBlogAdapter(getActivity());
        gridView.setAdapter(cateAdapter);
        listView.addHeaderView(headview);
        listView.setAdapter(adapter);

        gridView.setOnItemClickListener(this);

        RequestParams requestParams=new RequestParams(URL.URL_BLOG);
        requestParams.addBodyParameter("data","1");
        x.http().post(requestParams, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {

                List<BlogUi> list= JSON.parseArray(result,BlogUi.class);
                adapter.addAll(list);



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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        RequestParams params=new RequestParams(URL.URL_CateBLOG);
        params.addBodyParameter("data",list.get(i));
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<BlogUi> list= JSON.parseArray(result,BlogUi.class);

                adapter.addAll(list);

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
