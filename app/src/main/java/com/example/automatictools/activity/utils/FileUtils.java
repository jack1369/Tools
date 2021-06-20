package com.example.automatictools.activity.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    private ArrayList name;

    /*读取文件*/

    public String getBizid(String filePath, String msg) {

        String BIZID ="";

        if (filePath ==null)return BIZID;

        File file =new File(filePath);

        if (file.isDirectory()) {

            Log.d(msg, filePath +" is directory");

            return BIZID;

        }else {

            try {

                InputStream is =new FileInputStream(file);

                if (is !=null) {

                    InputStreamReader isr =new InputStreamReader(is);

                    BufferedReader br =new BufferedReader(isr);

                    String line;

                    while ((line = br.readLine()) !=null) {

                        if (line.contains("BIZID")) {

                            BIZID = line.split("=")[1];

                            return BIZID;

                        }

                        Log.i(msg, line);

                    }

                }

            }catch (FileNotFoundException e) {

                Log.i(msg, filePath +" doesn't found!");

            }catch (IOException e) {

                Log.i(msg, filePath +" read exception, " + e.getMessage());

            }

        }

        return BIZID;

    }

    /**

     * 初始化日志

     */

    public static String getInitLog(String filePath) {

        String initLog ="";

        if (filePath ==null)return initLog;

        File file =new File(filePath);

        if (file.isDirectory()) {

            Log.d(TAG, filePath +" is directory");

            return initLog;

        }else {

            try {

                InputStream is =new FileInputStream(file);

                if (is !=null) {

                    InputStreamReader isr =new InputStreamReader(is);

                    BufferedReader br =new BufferedReader(isr);

                    String line;

                    while ((line = br.readLine()) !=null) {

                        initLog = line;

                        Log.i(TAG, line);

                        return initLog;

                    }

                }

            }catch (FileNotFoundException e) {

                Log.i(TAG, filePath +" doesn't found!");

            }catch (IOException e) {

                Log.i(TAG, filePath +" read exception, " + e.getMessage());

            }

        }

        return initLog;

    }

    /**

     * 判断sdcard是否挂载

     *

     * @return

     */

    public static boolean hasSdcard() {

        String state = Environment.getExternalStorageState();

        return state.equals(Environment.MEDIA_MOUNTED);

    }

    /**

     * 获取sdcard中指定后缀的所有文件

     */

    private boolean isSdcardFile(String TAG) {

        name =new ArrayList();

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            File path = Environment.getExternalStorageDirectory();// 获得SD卡路径

            // File path = new File("/mnt/sdcard/");

            File[] files = path.listFiles();// 读取

            getFileName(files);

        }

        for (int i =0; i < name.size(); i++) {

        Log.d(TAG, "onCreaten-ame: " +name.get(i));

        if (name.get(i).toString().equals("{Name=profile}")) {

            Log.d(TAG, "onCreaten-ame-y: " +name.get(i));

            return true;

        }

    }

/* for (int i = 0; i < name.size(); i++) {

Log.i("zeng", "list. name: " + name.get(i));

}*/

        return false;

}

    private void getFileName(File[] files) {

        if (files !=null) {// 先判断目录是否为空，否则会报空指针

            for (File file : files) {

                if (file.isDirectory()) {

                    Log.i("zeng", "若是文件目录。继续读1" + file.getName().toString() + file.getPath().toString());

                    getFileName(file.listFiles());

                    Log.i("zeng", "若是文件目录。继续读2" + file.getName().toString() + file.getPath().toString());

                }else {

                    String fileName = file.getName();

                    if (fileName.endsWith(".dat")) {//以此后缀名的所有文件

                        HashMap map =new HashMap();

                        String s = fileName.substring(0, fileName.lastIndexOf(".")).toString();

                        Log.i("zeng", "文件名txt：：  " + s);

                        map.put("Name", fileName.substring(0, fileName.lastIndexOf(".")));

                        name.add(map);

                    }

                }

            }

        }

    }

    /**

     * 获取指定路径下的指定后缀所有文件

     */

    public static Vector getFileName(String fileAbsolutePath) {

        Vector vecFile =new Vector();

        File file =new File(fileAbsolutePath);

        File[] subFile = file.listFiles();

        for (int iFileLength =0; iFileLength < subFile.length; iFileLength++) {

// 判断是否为文件夹

            if (!subFile[iFileLength].isDirectory()) {

                String filename = subFile[iFileLength].getName();

                // 判断是否为.so结尾

                if (filename.trim().toLowerCase().endsWith(".so")) {

                    Log.d(TAG, "getSoFileName: " + filename);

                    vecFile.add(filename);

                }

            }

        }

        return vecFile;

    }

    /**

     * 判断配置文件是否存在

     *

     * @return

     */

    public static boolean fileIsExists(String filePath) {

        try {

            File f =new File(filePath);

            if (!f.exists()) {

                return false;

            }

        }catch (Exception e) {

            return false;

        }

        return true;

    }

    /**

     * 创建文件

     */

    public static String createFile(File file) {

        try {

            if (file.getParentFile().exists()) {

                Log.e(TAG, "----- 创建文件" + file.getAbsolutePath());

                file.createNewFile();

            }else {

//创建目录之后再创建文件

                createDir(file.getParentFile().getAbsolutePath());

                file.createNewFile();

                Log.e(TAG, "----- 创建文件" + file.getAbsolutePath());

            }

        }catch (Exception e) {

            e.printStackTrace();

        }

        return "";

    }

    public static String createDir(String dirPath) {

//因为文件夹可能有多层，比如:  a/b/c/ff.txt  需要先创建a文件夹，然后b文件夹然后...

        try {

            File file =new File(dirPath);

            if (file.getParentFile().exists()) {

                Log.e(TAG, "----- 创建文件夹" + file.getAbsolutePath());

                file.mkdir();

                return file.getAbsolutePath();

            }else {

                createDir(file.getParentFile().getAbsolutePath());

                Log.e(TAG, "----- 创建文件夹" + file.getAbsolutePath());

                file.mkdir();

            }

        }catch (Exception e) {

            e.printStackTrace();

        }

        return dirPath;

    }

    public static String readJsonFile(String filePath) {

        StringBuilder sb =new StringBuilder();

        try {

            File file =new File(filePath);

            InputStream in =null;

            in =new FileInputStream(file);

            int tempbyte;

            while ((tempbyte = in.read()) != -1) {

                sb.append((char) tempbyte);

            }

            in.close();

        }catch (Exception e) {

            Log.e(TAG, "readJsonFile: "+e.getMessage() );

            e.printStackTrace();

        }

        return sb.toString();

    }

//删除文件夹和文件夹里面的文件

    public static void deleteDir(final String pPath) {

        File dir =new File(pPath);

        deleteDirWihtFile(dir);

    }

    public static void deleteDirWihtFile(File dir) {

        if (dir ==null || !dir.exists() || !dir.isDirectory())

            return;

        for (File file : dir.listFiles()) {

            if (file.isFile())

                file.delete(); // 删除所有文件

            else if (file.isDirectory())

                deleteDirWihtFile(file); // 递规的方式删除文件夹

        }

//        dir.delete();// 删除目录本身

    }

}
