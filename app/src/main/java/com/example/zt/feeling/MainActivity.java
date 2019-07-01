package com.example.zt.feeling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.zt.feeling.Activity.WriteBlogActivity;
import com.example.zt.feeling.Fragment.BlogFragment;
import com.example.zt.feeling.Fragment.MenuRightFragment;
import com.example.zt.feeling.Fragment.MyBlogFragment;
import com.example.zt.feeling.Model.Blog;
import com.example.zt.feeling.tools.AnimatedRectActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.xutils.http.RequestParams;
import org.xutils.x;

public class MainActivity extends AppCompatActivity implements SlidingMenu.OnOpenedListener, SlidingMenu.OnClosedListener, RadioGroup.OnCheckedChangeListener {

    private SlidingMenu menu;
    private MenuRightFragment menuFragment;
    private BlogFragment blogFragment;

    private Button button;

    private RadioGroup radioGroup;

    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findView();
        // configure the SlidingMenu
         menu = new SlidingMenu(this);

        menu.setMode(SlidingMenu.LEFT);

        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);



        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);

        // 设置渐入渐出效果的值

        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        menu.attachToActivity(this, SlidingMenu.TOUCHMODE_FULLSCREEN);

        //为侧滑菜单设置布局
        menu.setMenu(R.layout.leftmeun);

        menuFragment = new MenuRightFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, menuFragment).commit();
        blogFragment = new BlogFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, blogFragment).commit();


        menu.setOnOpenedListener(this);
        menu.setOnClosedListener(this);

        radioButton.setChecked(true);



    }



    public void mainOnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.main_menu: menu.showMenu();break;
            case R.id.main_write:startActivity(new Intent(this, WriteBlogActivity.class));break;
        }

    }

    private void findView() {
        button= (Button) findViewById(R.id.main_menu);
        radioGroup= (RadioGroup) findViewById(R.id.main_group);
        radioGroup.setOnCheckedChangeListener(this);
        radioButton= (RadioButton) findViewById(R.id.mainAll_blog);



    }
   //监听菜单打开事件
    @Override
    public void onOpened() {
        button.setText("主页");



    }
  //监听菜单关闭事件
    @Override
    public void onClosed() {
        button.setText("菜单");

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i==R.id.mainAll_blog)
        {
          BlogFragment b = new BlogFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, b).commit();



        }else if(i==R.id.mainMy_blog)
        {
            MyBlogFragment mb=new MyBlogFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, mb).commit();

        }

    }
}

