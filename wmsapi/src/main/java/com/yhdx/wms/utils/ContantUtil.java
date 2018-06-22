package com.yhdx.wms.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 从配置文件中读取配置
 */
public class ContantUtil {

    private ContantUtil() {
    }

    private static Logger logger = LoggerFactory.getLogger(ContantUtil.class);

    public static Properties getProperties() {
        URL location = ContantUtil.class.getProtectionDomain().getCodeSource().getLocation();
        String path = location.getPath();
        path = path.substring(1, path.lastIndexOf("/") + 1).concat("application.properties");
        Properties pro = new Properties();
        try (FileInputStream in = new FileInputStream(path);) {
            pro.load(in);
        } catch (Exception e) {
            logger.error("target [getConfig] " + e.getMessage());
        }
        return pro;
    }

    public static void setProperties(String key,String value) throws IOException{
        URL location = ContantUtil.class.getProtectionDomain().getCodeSource().getLocation();
        String path = location.getPath();
        path = path.substring(1, path.lastIndexOf("/") + 1).concat("application.properties");
        try {
            Properties prop = new Properties();
            InputStream fis = new FileInputStream(path);
            prop.load(fis);
            OutputStream fos = new FileOutputStream(path);
            prop.setProperty(key, value);
            prop.store(fos,value);
        } catch (FileNotFoundException e) {
            System.err.println("Visit " + path + " for updating " + "ddd"
                    + " value error");
        }


    }


}

