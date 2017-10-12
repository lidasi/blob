package com.example.hitest.controller;

import com.example.hitest.Util.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class test {
    public static void main(String args[] ){
        /*DateUtil.getNow17();

        getDistanceTime(DateUtil.getDateCross(DateUtil.getNow17()), DateUtil.getDateCross("20171004233318438"));*/

        /*RandomHan han = new RandomHan();
        String nickName = "";
        for (int i = 0; i < 5; i++) {
            nickName+=han.getRandomHan();
        }
        System.out.print(nickName);*/
        /*int count = 0;
        File file = new File("D:/phrase.txt");
        String phraseString = txt2String(file);
        String[] array = phraseString.split(",");
        for (int i = 0; i < array.length; i++) {
            count += 1;
            System.out.println(array[i]);
        }
        System.out.print(count);*/
        rticleTest();

    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String str1, String str2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long res = day * 24 * 60 * 60 + hour * 60 * 60 + min * 60 + sec;
        System.out.println(res);
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void rticleTest(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject lan1 = new JSONObject();
        lan1.put("id", 1);
        lan1.put("content", "今天天气不错");
        lan1.put("pid", 0);
        jsonArray.add(lan1);

        JSONObject lan2 = new JSONObject();
        lan2.put("id", 2);
        lan2.put("content", "今天天气挺好");
        lan2.put("pid", 0);
        jsonArray.add(lan2);

        JSONObject lan3 = new JSONObject();
        lan3.put("id", 3);
        lan3.put("content", "你说的很对");
        lan3.put("pid", 1);
        jsonArray.add(lan3);

        JSONObject lan4 = new JSONObject();
        lan4.put("id", 4);
        lan4.put("content", "明天天气也很不错");
        lan4.put("pid", 2);
        jsonArray.add(lan4);

        jsonObject.put("language", jsonArray);
        String stringObj = jsonObject.getString("language");
        System.out.print(jsonArray.get(2));

    }
}
