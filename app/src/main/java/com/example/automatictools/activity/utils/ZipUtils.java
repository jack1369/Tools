package com.example.automatictools.activity.utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    public static final String TAG ="ZIP";

    public ZipUtils() {

    }

    /**

     * 解压缩功能. 将zipFile文件解压到folderPath目录下.

     *

     * @throws Exception

     */

    public static void upZipFile(String zipFile, String folderPath)

            throws ZipException, IOException {

        ZipFile zfile =new ZipFile(zipFile);

        Enumeration zList = zfile.entries();

        ZipEntry ze =null;

        byte[] buf =new byte[1024];

        while (zList.hasMoreElements()) {

            ze = (ZipEntry) zList.nextElement();

            if (ze.isDirectory()) {

                Log.e("upZipFile", "ze.getName() = " + ze.getName());

                String dirstr = folderPath + ze.getName();

                // dirstr.trim();

                dirstr =new String(dirstr.getBytes("8859_1"), "GB2312");

                Log.d("upZipFile", "str = " + dirstr);

                File f =new File(dirstr);

                f.mkdir();

                continue;

            }

            Log.d("upZipFile", "ze.getName() = " + ze.getName());

            OutputStream os =new BufferedOutputStream(new FileOutputStream(

                    getRealFileName(folderPath, ze.getName())));

            InputStream is =new BufferedInputStream(zfile.getInputStream(ze));

            int readLen =0;

            while ((readLen = is.read(buf, 0, 1024)) != -1) {

                os.write(buf, 0, readLen);

            }

            is.close();

            os.close();

        }

        zfile.close();

        Log.d("upZipFile", "finishssssssssssssssssssss");

    }

    /**

     * 给定根目录，返回一个相对路径所对应的实际文件名.

     *

     * @param baseDir

     *            指定根目录

     * @param absFileName

     *            相对路径名，来自于ZipEntry中的name

     * @return java.io.File 实际的文件

     */

    public static File getRealFileName(String baseDir, String absFileName) {

        String[] dirs = absFileName.split("/");

        File ret =new File(baseDir);

        String substr =null;

        if (dirs.length >1) {

            for (int i =0; i < dirs.length -1; i++) {

                substr = dirs[i];

                try {

// substr.trim();

                    substr =new String(substr.getBytes("8859_1"), "GB2312");

                }catch (UnsupportedEncodingException e) {

// TODO Auto-generated catch block

                    e.printStackTrace();

                }

                ret =new File(ret, substr);

            }

            Log.d("upZipFile", "1ret = " + ret);

            if (!ret.exists())

                ret.mkdirs();

            substr = dirs[dirs.length -1];

            try {

// substr.trim();

                substr =new String(substr.getBytes("8859_1"), "GB2312");

                Log.d("upZipFile", "substr = " + substr);

            }catch (UnsupportedEncodingException e) {

// TODO Auto-generated catch block

                e.printStackTrace();

            }

            ret =new File(ret, substr);

            Log.d("upZipFile", "2ret = " + ret);

            return ret;

        }

        return ret;

    }

    /**

     * 解压zip到指定的路径

     *

     * @param zipFileString ZIP的名称

     * @param outPathString 要解压缩路径

     * @throws Exception

     */

    public static void UnZipFolder(String zipFileString, String outPathString)throws Exception {

        ZipInputStream inZip =new ZipInputStream(new FileInputStream(zipFileString));

        ZipEntry zipEntry;

        String szName ="";

        while ((zipEntry = inZip.getNextEntry()) !=null) {

            szName = zipEntry.getName();

            if (zipEntry.isDirectory()) {

//获取部件的文件夹名

                szName = szName.substring(0, szName.length() -1);

                File folder =new File(outPathString + File.separator + szName);

                folder.mkdirs();

            }else {

                Log.e(TAG, outPathString + File.separator + szName);

                File file =new File(outPathString + File.separator + szName);

                if (!file.exists()) {

                    Log.e(TAG, "Create the file:" + outPathString + File.separator + szName);

                    file.getParentFile().mkdirs();

                    file.createNewFile();

                }

// 获取文件的输出流

                FileOutputStream out =new FileOutputStream(file);

                int len;

                byte[] buffer =new byte[1024];

                // 读取（字节）字节到缓冲区

                while ((len = inZip.read(buffer)) != -1) {

// 从缓冲区（0）位置写入（字节）字节

                    out.write(buffer, 0, len);

                    out.flush();

                }

                out.close();

            }

        }

        inZip.close();

    }

    public static void UnZipFolder(String zipFileString, String outPathString, String szName)throws Exception {

        ZipInputStream inZip =new ZipInputStream(new FileInputStream(zipFileString));

        ZipEntry zipEntry;

        while ((zipEntry = inZip.getNextEntry()) !=null) {

//szName = zipEntry.getName();

            if (zipEntry.isDirectory()) {

//获取部件的文件夹名

                szName = szName.substring(0, szName.length() -1);

                File folder =new File(outPathString + File.separator + szName);

                folder.mkdirs();

            }else {

                Log.e(TAG, outPathString + File.separator + szName);

                File file =new File(outPathString + File.separator + szName);

                if (!file.exists()) {

                    Log.e(TAG, "Create the file:" + outPathString + File.separator + szName);

                    file.getParentFile().mkdirs();

                    file.createNewFile();

                }

// 获取文件的输出流

                FileOutputStream out =new FileOutputStream(file);

                int len;

                byte[] buffer =new byte[1024];

                // 读取（字节）字节到缓冲区

                while ((len = inZip.read(buffer)) != -1) {

// 从缓冲区（0）位置写入（字节）字节

                    out.write(buffer, 0, len);

                    out.flush();

                }

                out.close();

            }

        }

        inZip.close();

    }

    /**

     * 压缩文件和文件夹

     *

     * @param srcFileString 要压缩的文件或文件夹

     * @param zipFileString 解压完成的Zip路径

     * @throws Exception

     */

    public static void ZipFolder(String srcFileString, String zipFileString)throws Exception {

//创建ZIP

        ZipOutputStream outZip =new ZipOutputStream(new FileOutputStream(zipFileString));

        //创建文件

        File file =new File(srcFileString);

        //压缩

        Log.e(TAG, "ZipFolder: " +file.getParent()+"==="+file.getAbsolutePath());

        ZipFiles(file.getParent()+ File.separator, file.getName(), outZip);

        //完成和关闭

        outZip.finish();

        outZip.close();

    }

    /**

     * 压缩文件

     *

     * @param folderString

     * @param fileString

     * @param zipOutputSteam

     * @throws Exception

     */

    private static void ZipFiles(String folderString, String fileString, ZipOutputStream zipOutputSteam)throws Exception {

        Log.e(TAG, "ZipFiles: " + folderString +"\n" +

                "fileString:" + fileString +"\n==========================");

        if (zipOutputSteam ==null)

            return;

        File file =new File(folderString + fileString);

        if (file.isFile()) {

            ZipEntry zipEntry =new ZipEntry(fileString);

            FileInputStream inputStream =new FileInputStream(file);

            zipOutputSteam.putNextEntry(zipEntry);

            int len;

            byte[] buffer =new byte[4096];

            while ((len = inputStream.read(buffer)) != -1) {

                zipOutputSteam.write(buffer, 0, len);

            }

            zipOutputSteam.closeEntry();

        }else {

//文件夹

            String fileList[] = file.list();

            //没有子文件和压缩

            if (fileList.length <=0) {

                ZipEntry zipEntry =new ZipEntry(fileString + File.separator);

                zipOutputSteam.putNextEntry(zipEntry);

                zipOutputSteam.closeEntry();

            }

//子文件和递归

            for (int i =0; i < fileList.length; i++) {

                ZipFiles(folderString+fileString+"/",  fileList[i], zipOutputSteam);

            }

        }

    }

    /**

     * 返回zip的文件输入流

     *

     * @param zipFileString zip的名称

     * @param fileString    ZIP的文件名

     * @return InputStream

     * @throws Exception

     */

    public static InputStream UpZip(String zipFileString, String fileString)throws Exception {

        ZipFile zipFile =new ZipFile(zipFileString);

        ZipEntry zipEntry = zipFile.getEntry(fileString);

        return zipFile.getInputStream(zipEntry);

    }

    /**

     * 返回ZIP中的文件列表（文件和文件夹）

     *

     * @param zipFileString  ZIP的名称

     * @param bContainFolder 是否包含文件夹

     * @param bContainFile  是否包含文件

     * @return

     * @throws Exception

     */

    public static List GetFileList(String zipFileString, boolean bContainFolder, boolean bContainFile)throws Exception {

        List fileList =new ArrayList();

        ZipInputStream inZip =new ZipInputStream(new FileInputStream(zipFileString));

        ZipEntry zipEntry;

        String szName ="";

        while ((zipEntry = inZip.getNextEntry()) !=null) {

            szName = zipEntry.getName();

            if (zipEntry.isDirectory()) {

// 获取部件的文件夹名

                szName = szName.substring(0, szName.length() -1);

                File folder =new File(szName);

                if (bContainFolder) {

                    fileList.add(folder);

                }

            }else {

                File file =new File(szName);

                if (bContainFile) {

                    fileList.add(file);

                }

            }

        }

        inZip.close();

        return fileList;

    }

}