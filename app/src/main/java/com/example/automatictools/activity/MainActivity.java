package com.example.automatictools.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

import com.example.automatictools.R;
import com.example.automatictools.activity.service.SmartViewService;
import com.example.automatictools.activity.utils.JsonUtils;
import com.example.automatictools.activity.utils.ZipUtils;
import com.hjq.xtoast.XToast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends Activity implements View.OnClickListener {

    private String TAG = "LoginActivity";
    private Button mBtCheck,mBtStart;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();

        initData();

    }

    private void initData() {





    }

    private void initView() {
        mBtCheck = findViewById(R.id.bt_check);//启动
        mBtStart = findViewById(R.id.bt_start);//启动

        mBtCheck.setOnClickListener(this);
        mBtStart.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_check:
                Log.e(TAG, "onClick: R.id.bt_check:" );

                break;
            case R.id.bt_start:
                Log.e(TAG, "onClick: R.id.bt_start:" );
                SmartViewService.start(this);
                break;
        }
    }
}