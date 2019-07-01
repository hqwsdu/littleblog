package com.example.zt.feeling.Activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zt.feeling.R;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_greet)
public class GreetActivity extends AppCompatActivity implements View.OnClickListener {
    @ViewInject(R.id.greet_button)
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        button.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.greet_button :
                startActivity(new Intent(this,LoginActivity.class));
                overridePendingTransition(R.anim.slide_left_out,R.anim.slide_right_in);
                finish();

                break;

        }

    }
}
