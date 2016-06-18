package com.yiluxiangbei.joey.haoqiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yiluxiangbei.joey.haoqiu.R;


public class AboutActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private int colorWhite=0Xffffffff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        toolbar= (Toolbar) findViewById(R.id.toolbar_about);
        setSupportActionBar(toolbar);
        toolbar.setTitle("关于");
        toolbar.setTitleTextColor(colorWhite);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
