package com.mtool.toolslib.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by admin on 2017/12/26.
 */

public class FileUtil {
    /**
     * Get image from newwork
     */
    public static InputStream getImageStream(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return conn.getInputStream();
        }
        return null;
    }


    /**
     * 保存文件
     */
    public static void saveFile(Bitmap bm, String fileName, String filePath) throws IOException {
        File dirFile = new File(filePath);
//        BaseApi.IMAGE_URL
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(filePath + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }

    /**
     * 加载本地图片
     */
    public static Bitmap getLocalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除指定文件
     */
    public static void deleteFile(String url) {
        File file = new File(url);
        if (file.exists()) {
            file.delete();
        }
    }
}
