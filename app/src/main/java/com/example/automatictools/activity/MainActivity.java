package com.example.automatictools.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.automatictools.R;
import com.example.automatictools.activity.adapter.MyAdapter;
import com.example.automatictools.activity.adapter.MyViewPager;
import com.example.automatictools.activity.service.SmartViewService;
import com.example.automatictools.activity.utils.JsonUtils;
import com.example.automatictools.activity.utils.ZipUtils;
import com.example.automatictools.activity.view.MyFragment;
import com.example.automatictools.activity.view.ToolFragment;
import com.hjq.xtoast.XToast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private String TAG = "LoginActivity";
    private Button mBtCheck,mBtStart;
    private MyViewPager view_pager;
    private List<Fragment> list;
    private TextView mtv_tool;
    private TextView mtv_my;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();

        initData();

    }

    private void initData() {
        list = new ArrayList();
        MyFragment myFragment = new MyFragment();
        ToolFragment toolFragment = new ToolFragment();
        list.add(toolFragment);
        list.add(myFragment);
        MyAdapter adapter=new MyAdapter(getSupportFragmentManager(), list);
        view_pager.setAdapter(adapter);

    }

    private void initView() {
        mBtCheck = findViewById(R.id.bt_check);//启动
        mBtStart = findViewById(R.id.bt_start);//启动
        mtv_tool = findViewById(R.id.tv_tool);
        mtv_my = findViewById(R.id.tv_my);
        view_pager = findViewById(R.id.pager);

        mtv_tool.setOnClickListener(this);
        mtv_my.setOnClickListener(this);
//        mBtCheck.setOnClickListener(this);
//        mBtStart.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tool:
                Log.e(TAG, "onClick: R.id.bt_check:" );
                view_pager.setCurrentItem(0);
                break;
            case R.id.tv_my:
                Log.e(TAG, "onClick: R.id.bt_start:" );
                view_pager.setCurrentItem(1);
//                SmartViewService.start(this);
                break;
        }
    }
}