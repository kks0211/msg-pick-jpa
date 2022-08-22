package com.msgpick.msgpick.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

    public static File folderCheck(String uploadPath, String path) {

        File folder = new File(uploadPath, path);

        if (!folder.exists()) {
            try {
                folder.mkdirs();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return folder;
    }

    public static String pathCheck() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);

        return str.replace("-", File.separator);
    }
}
