package com.example.zt.feeling;

import android.app.Application;

import com.example.zt.feeling.Model.BlogUser;

import org.xutils.x;

/**
 * Created by zt on 2016/4/13.
 */
public class MyApp extends Application {
    private BlogUser blogUser;

    public BlogUser getBlogUser() {
        return blogUser;
    }

    public void setBlogUser(BlogUser blogUser) {
        this.blogUser = blogUser;
    }

    @Override

    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
