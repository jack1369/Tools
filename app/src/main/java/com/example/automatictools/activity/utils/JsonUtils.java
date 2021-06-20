package com.example.automatictools.activity.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;

public class JsonUtils {

    private static String TAG ="JsonUtils";

    public static void writeJson(final String filePath, LinkedHashMap titleMap, LinkedHashMap detailMap, LinkedHashMap passMap) {

        final File file =new File(filePath);

        try {

            final JSONObject root =new JSONObject();//实例一个JSONObject对象

            root.put("time", "2020");//对其添加一个数据

            JSONArray languages =new JSONArray();//实例一个JSON数组

            setJson(0,languages,titleMap,detailMap,passMap,"version_title","version_detail","version_result");

            setJson(1,languages,titleMap,detailMap,passMap,"init_title","init_detail","init_result");

            setJson(2,languages,titleMap,detailMap,passMap,"network_title","network_detail","network_result");

            setJson(2,languages,titleMap,detailMap,passMap,"install_title","install_path","install_result");

            setJson(3,languages,titleMap,detailMap,passMap,"sign_title","signature_detail","sign_result");

            setJson(4,languages,titleMap,detailMap,passMap,"profile_title","profile_detail","profile_result");

            setJson(5,languages,titleMap,detailMap,passMap,"iotLog_title","iotLog_detail","iotLog_result");

//            JSONObject versionJsdon = new JSONObject();//实例一个lan1的JSON对象

//

//            versionJsdon.put("title", titleMap.get("version_title"));//对lan1对象添加数据

//

//            versionJsdon.put("detail", detailMap.get("version_detail"));//对lan1对象添加数据

//

//            versionJsdon.put("pass", passMap.get("version_result"));//对lan1对象添加数据

//

//            JSONObject initJsdon = new JSONObject();//实例一个lan1的JSON对象

//

//            initJsdon.put("title", titleMap.get("init_title"));//对lan1对象添加数据

//

//            initJsdon.put("detail", detailMap.get("init_detail"));//对lan1对象添加数据

//

//            initJsdon.put("pass", passMap.get("init_result"));//对lan1对象添加数据

//

//            languages.put(0, lan1);//将lan1对象添加到JSON数组中去，角标为0

//

//            languages.put(1, lan2);//将lan2对象添加到JSON数组中去，角标为1

//

//            languages.put(2, lan3);//将lan3对象添加到JSON数组中去，角标为2

            root.put("check", languages);//然后将JSON数组添加到名为root的JSON对象中去

            //判断文件是否存在  不存在则创建

            Log.e(TAG, "writeJson: "+ root.toString());

            if (!FileUtils.fileIsExists(filePath)) {

                Log.e(TAG, "writeJson: " + filePath);

                FileUtils.createFile(file);

            }

//写入文本

            new Thread(new Runnable() {

                @Override

                public void run() {

                    FileOutputStream fos =null;//创建一个文件输出流

                    try {

                        fos =new FileOutputStream(filePath);

                        fos.write(root.toString().getBytes());//将生成的JSON数据写出

                        fos.close();//关闭输出流

                    }catch (Exception e) {

                        Log.e(TAG, "run: "+e.getMessage() );

                        e.printStackTrace();

                    }

                }

            }).start();

        }catch (JSONException e) {

            Log.e(TAG, "writeJson: "+e.getMessage() );

            e.printStackTrace();

        }

    }

    public static void setJson(int indext,JSONArray languages,LinkedHashMap titleMap, LinkedHashMap detailMap, LinkedHashMap passMap,String titleName,String detailName,String passName) {

        JSONObject json =new JSONObject();//实例一个lan1的JSON对象

        try {

            json.put("title", titleMap.get(titleName));//对lan1对象添加数据

            json.put("detail", detailMap.get(detailName));//对lan1对象添加数据

            json.put("pass", passMap.get(passName));//对lan1对象添加数据

            languages.put(indext, json);

        }catch (JSONException e) {

            Log.e(TAG, "setJson: "+e.getMessage() );

            e.printStackTrace();

        }

    }

    public static String readJson(String filePath) {

        return FileUtils.readJsonFile(filePath);

    }

}