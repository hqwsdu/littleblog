package com.example.zt.feeling.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zt.feeling.Model.BlogUser;
import com.example.zt.feeling.R;
import com.example.zt.feeling.tools.URL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class Register2Activity extends AppCompatActivity implements View.OnClickListener {
    private String phone;
    private EditText pass1,pass2;
    private Button button,back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        Intent intent=getIntent();
        phone=intent.getStringExtra("phone");
        init();
    }

    private void init() {
        pass1= (EditText) findViewById(R.id.register_passoword);
        pass2= (EditText) findViewById(R.id.register_passoword2);
        button= (Button) findViewById(R.id.register_ok);
        back= (Button) findViewById(R.id.register2_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.slide_left_out,R.anim.slide_right_in);
                finish();
            }
        });
        button.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        String password1=pass1.getText().toString();
        String password2=pass2.getText().toString();
        if(TextUtils.isEmpty(password1)||TextUtils.isEmpty(password2))
        {
            Toast.makeText(this,"输入不能为空",Toast.LENGTH_LONG).show();
        }
        else if(password1.equals(password2))
        {
            RequestParams requestParams=new RequestParams(URL.URL_REGISTER);;
            BlogUser blog=new BlogUser();
            blog.setBloguser_phone(phone);
            blog.setBloguser_password(password2);

            requestParams.addBodyParameter("phone",blog.getBloguser_phone());
            requestParams.addBodyParameter("pass",blog.getBloguser_password());
            x.http().get(requestParams, new Callback.CacheCallback<String>() {
                @Override
                public void onSuccess(String result) {
                   Toast.makeText(Register2Activity.this,result,Toast.LENGTH_LONG).show();
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
        else
        {
            Toast.makeText(this,"两次密码输入不一致",Toast.LENGTH_LONG).show();
        }


    }
}
