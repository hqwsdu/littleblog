package com.example.zt.feeling.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zt.feeling.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity implements Handler.Callback{
    @ViewInject(R.id.register_v)
    private Button button_v;
    @ViewInject(R.id.register_phone)
    private EditText editText_phone;
    @ViewInject(R.id.register_code)
    private EditText editText_code;
    private EventHandler eventHandler;
    @ViewInject(R.id.register_back)
    private Button button_back;

    private Handler myhandler=new Handler(this);

    private Timer timer;

    private String t_phone;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           if(msg.what>0)
           {
               button_v.setText("剩余"+msg.what+"秒");
               button_v.setClickable(false);
           }
            else
           {
               button_v.setText("点击获取验证码");
               button_v.setClickable(true);
           }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        SMSSDK.initSDK(this,"12ba2772fb488", "0709807c895beffe0185ea82ca16e3fb");
        eventHandler=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        Log.i("data","提交验证码成功");
                        Message message=Message.obtain();
                        message.what=1;
                        myhandler.sendMessage(message);
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        Log.i("data","获取验证码成功");
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }
            }

        };
        SMSSDK.registerEventHandler(eventHandler);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.slide_left_out,R.anim.slide_right_in);
                finish();
            }
        });

    }
    public void registerOnClick(View view)
    {
        String phone=editText_phone.getText().toString();
        String code=editText_code.getText().toString();
        switch (view.getId())
        {

            case R.id.register_v:


                if(!TextUtils.isEmpty(phone))
                {
                    SMSSDK.getVerificationCode("86",phone);
                    t_phone=phone;
                    timer=new Timer();
                    ;
                    TimerTask timerTask=new TimerTask() {
                        int  i=60;

                        @Override
                        public void run() {
                            Message message=new Message();
                            message.what= i--;
                            if(message.what>=0)
                            {
                                handler.sendMessage(message);
                            }


                        }
                    };
                    timer.schedule(timerTask, 1000,1000);
                }
                else
                {
                    Toast.makeText(this,"手机号不能为空",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.register_next:
                if(!TextUtils.isEmpty(code))
                {
                    SMSSDK.submitVerificationCode("86",phone,code);
                }



                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @Override
    public boolean handleMessage(Message message) {
        if(message.what==1)
        {

            Toast.makeText(this,"验证码验证成功",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(RegisterActivity.this,Register2Activity.class);
            intent.putExtra("phone",t_phone);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_left_out,R.anim.slide_right_in);
            finish();

        }
        return false;
    }
}
