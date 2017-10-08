/**
 * PropUtil.java
 */
package com.example.hitest.Util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


/**
 * [name]<br>
 * 基本设定相关共通函数<br>
 * <br>
 * [function]<br>
 * 从属性文件或者数据库取得相应的设定信息<br>
 * <br>
 * [history]<br>
 * 2012/02/11 ver1.00 JiangJusheng<br>
 */
public class PropUtil {

    /**
     * 设定信息保存用
     */
    public static HashMap<String, String> innConfig = new HashMap<String, String>();

    public static Map<String, String> entActionAuthData = new HashMap<String, String>();

    public static void setProperty(String key, String value) {
        innConfig.put(key, value);
    }

    @SuppressWarnings("rawtypes")
    public static void initProp(String filePath) {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            // 读取属性文件a.properties
            in = new BufferedInputStream(new FileInputStream(filePath));
            prop.load(in);

            // 加载属性列表
            for (Iterator iter = prop.entrySet().iterator(); iter.hasNext();) {
                Map.Entry entry = (Map.Entry) iter.next();
                innConfig.put((String) entry.getKey(), (String) entry.getValue());
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 获取设定信息<br>
     * 若该KEY对应的键值不存在，则返回空白字符串
     *
     * @param key
     *            KEY
     *
     * @return String 设定信息
     */
    public static String getProperty(String key) {
        String strs = innConfig.get(key);
        if (strs == null) {
            return "";
        } else {
            return strs.trim();
        }
    }

    /**
     * 读取常量表数据 加载到常量中
     * @param systemEnvService
     */
   /* public static void init(SystemEnvService systemEnvService) {
        List<SystemEnv> lst = systemEnvService.getAll();
        
        // 如果获取的 list 有结果
        if(null != lst && lst.size() > 0) {
            for(SystemEnv obj : lst) {
                innConfig.put(obj.getItem(), obj.getValue());
            }
        }
        
    }*/
}
