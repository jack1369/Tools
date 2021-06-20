package com.example.automatictools.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.automatictools.R;
import com.example.automatictools.activity.utils.Constant;
import com.example.automatictools.activity.utils.JsonUtils;
import com.example.automatictools.activity.utils.ZipUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class SplashActivity extends AppCompatActivity {
    private String TAG ="SplashActivity";
    private String path;
    Handler mHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initData();


    }

    private void initData() {
        getAndroiodScreenProperty();
        path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.e(TAG, "initData: path="+path );
//        动态检查申请权限 悬浮窗、网络、读写存储
        checkPermission();


//        下载文件

//        new Thread(new Runnable() {
//
//            @Override
//
//            public void run() {
//
//                downloadData();
//                unZipData();
//
//                getJson(path + "/master/DataSave-master/data.json");
//
////                        downloadData();
//
//            }
//
//        }).start();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent();
                intent.setClass(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();//销毁欢迎页面
            }
        },3000);//3秒后跳转
    }
    /**
     * 悬浮窗权限*/
    private void checkPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (!Settings.canDrawOverlays(this)) {

                Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);

                startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + "com.example.automatictools")), 0);

                Log.e(TAG, "check: 当前没有悬浮窗权限");

            } else {

                Log.e(TAG, "check: 当前有悬浮窗权限");

                //回到桌面
//                moveTaskToBack(true);
                //显示悬浮窗

            }

        }

    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (!Settings.canDrawOverlays(this)) {

                    Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();

                } else {

                    Log.e(TAG, "onActivityResult: 成功");

                    //延迟1s

//                    new Handler().postDelayed({

//                            val intent = Intent(this @Main2Activity,FloatWinfowServices:: class.java)

//                    intent.putExtra("rangeTime", rangeTime)

//                    hasBind = bindService(intent, mVideoServiceConnection, Context.BIND_AUTO_CREATE)

//                    moveTaskToBack(true)

//                },1000);

                }

            }

        }

    }

    /**下载文件*/
    public void downloadData() {

        try {

            //下载路径，如果路径无效了，可换成你的下载路径

            //            String url = "https://gw.alipayobjects.com/os/bmw-prod/8c379c93-8c76-41fd-adaa-9d703b16c6da.apk";

            //            String url = "https://github.com/xyxyLiu/PluginM.git";

            //            String url = "https://github.com/xyxyLiu/PluginM/archive/master.zip";

            String url = "https://github.com/jack1369/DataSave/archive/master.zip";

            Log.e(TAG, "downloadData: " + path);

            final long startTime = System.currentTimeMillis();

            Log.e(TAG, "downloadData-startTime=" + startTime);

            //下载函数

            String filename = url.substring(url.lastIndexOf("/") + 1);

            //获取文件名

            URL myURL = new URL(url);

            URLConnection conn = myURL.openConnection();

            conn.connect();

            InputStream is = conn.getInputStream();

            int fileSize = conn.getContentLength();//根据响应获取文件大小

            //    if (fileSize <= 0) throw new RuntimeException("无法获知文件大小");

            if (is == null) throw new RuntimeException("stream is null");

            File file1 = new File(path);

            if (!file1.exists()) {

                file1.mkdirs();

            }

            //把数据存入路径+文件名

            FileOutputStream fos = new FileOutputStream(path + "/" + filename);

            byte buf[] = new byte[1024];

            int downLoadFileSize = 0;

            do {

                //循环读取

                int numread = is.read(buf);

                if (numread == -1) {

                    break;

                }

                fos.write(buf, 0, numread);

                downLoadFileSize += numread;

                //更新进度条

            } while (true);

            Log.e(TAG, "downloadData-download success");

            Log.e(TAG, "downloadData-totalTime=" + (System.currentTimeMillis() - startTime));

            //下载成功后 解压

            is.close();

//            unZipData();

//            getJson(path + "/master/DataSave-master/data.json");

        } catch (Exception ex) {

            Log.e(TAG, "downloadData: 请检查网络权限是否打开已经网络是否正常");

            Log.e(TAG, "downloadData-error: " + ex.getMessage(), ex);

        }

    }
    /**解压文件*/
    private void unZipData() {

        //解压文件

        Log.e(TAG, "downloadData: " + path + "/master.zip");

        try {

            ZipUtils.upZipFile(path + "/master.zip", path + "/master");

            //      ZipUtils.upZipFile( "sdcard/alipay/uplog.zip", "sdcard/alipay");

        } catch (IOException e) {

            Log.e(TAG, "downloadData: " + e.getMessage());

            e.printStackTrace();

        }

    }

    private void getJson(String filePath) {

        Log.e(TAG, "getJson: filePath=" + filePath);

        String jsonList = JsonUtils.readJson(filePath);

        Log.e(TAG, "getJson: " + jsonList);

        JSONObject root = null;//用JSONObject进行解析

        try {

            root = new JSONObject(jsonList);

            String time = root.getString("time");//获取字符串类型的键值对

            Log.e(TAG, "getJson: time=" + time);

            JSONArray array = root.getJSONArray("data");//获取JSON数据中的数组数据

            Log.e(TAG, "getJson: array=" + array.toString());

            for (int i = 0; i < array.length(); i++) {

                JSONObject object = array.getJSONObject(i);//遍历得到数组中的各个对象

                //设置编码

                String item_title = new String(object.getString("device_sn").getBytes("iso-8859-1"), "utf-8");

//                String item_detail =new String(object.getString("detail").getBytes("iso-8859-1"), "utf-8");

                boolean item_pass = Boolean.valueOf(new String(object.get("isuse").toString().getBytes("iso-8859-1"), "utf-8"));

                Log.e(TAG, "getJson:on_trial_start_time= " + item_title + "isuse=" + item_pass);

            }

        } catch (Exception e) {

            Log.e(TAG, "getJson: Exception=" + e.getMessage());

            e.printStackTrace();

        }

    }
    public void getAndroiodScreenProperty(){
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;// 屏幕宽度（像素）
        int height= dm.heightPixels; // 屏幕高度（像素）
        float density = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width/density);//屏幕宽度(dp)
        int screenHeight = (int)(height/density);//屏幕高度(dp)
        Constant.screenWidth=width;
        Constant.screenHeight=height;
        Log.e("123", screenWidth + "======" + screenHeight);
    }
}