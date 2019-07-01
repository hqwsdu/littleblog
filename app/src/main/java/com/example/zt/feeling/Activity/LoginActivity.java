package com.example.zt.feeling.Activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.example.zt.feeling.MainActivity;
import com.example.zt.feeling.Model.BlogUser;
import com.example.zt.feeling.MyApp;
import com.example.zt.feeling.R;
import com.example.zt.feeling.tools.URL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @ViewInject(R.id.loggin_button)
    private Button button;
    @ViewInject(R.id.user_phone)
    private EditText user_phone;
    @ViewInject(R.id.user_pass)
    private EditText user_pass;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        init();

        button.setOnClickListener(this);

    }

    private void init() {

    }
    private BlogUser getUserData()
    {
        BlogUser blogUser=new BlogUser();
        String user_name=user_phone.getText().toString();
        String user_password=user_pass.getText().toString();
        if(TextUtils.isEmpty(user_name)||TextUtils.isEmpty(user_password))
        {
            Toast.makeText(this,"用户名或密码为空，请重新输入",Toast.LENGTH_LONG).show();
        }
        else
        {

            blogUser.setBloguser_phone(user_name);
            blogUser.setBloguser_password(user_password);
            user_phone.setText("");
            user_pass.setText("");

        }

        return blogUser;

    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId())
        {
            case R.id.login_other:

                finish();


                break;
            case R.id.loggin_button:


                RequestParams params=new RequestParams (URL.URL_LOGIN);
                Log.i("Tag",URL.URL_LOGIN);
                BlogUser blogUser=getUserData();
                params.addBodyParameter("phone",blogUser.getBloguser_phone());
                params.addBodyParameter("pass",blogUser.getBloguser_password());
                x.http().get(params, new Callback.CacheCallback<String>() {

                    @Override
                    public void onSuccess(String result) {

                        Log.i("TAG",result);
                        BlogUser blogUser1=JSON.parseObject(result,BlogUser.class);
                        if(blogUser1.getBloguser_id()!=0) {
                            MyApp myApp = (MyApp) getApplication();
                            myApp.setBlogUser(blogUser1);

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_left_out, R.anim.slide_right_in);


                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                        }
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



            break;
            case R.id.login_re:
                intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_left_out,R.anim.slide_right_in);

                break;
        }



    }
}
