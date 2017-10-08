package com.example.hitest.Util;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;



public class HttpUtil {
    /**
     * instances of the log class
     */
    private static Logger logger = Logger.getLogger(HttpUtil.class);

    /**
     * 从输入的数据流中得到JSON字符串<br>
     * 不能取到时返回Null
     *
     * @param request Http请求对象
     *
     * @return String JSON字符串
     */
    public static String getStringFromRequest(HttpServletRequest request) {
        ServletInputStream input = null;
        ByteArrayOutputStream outStream = null;
        String inputstr = null;
        try {
            input = request.getInputStream();
            outStream = new ByteArrayOutputStream();
            int len = -1;
            byte[] buffer = new byte[1024];
            while ((len = input.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            inputstr = new String(outStream.toByteArray(), "utf-8");

        } catch (IOException ioexp) {
            logger.error("解析Http请求时异常", ioexp);
            return null;
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException ioexp) {
                    logger.error(ioexp.getMessage());
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ioexp) {
                    logger.error(ioexp.getMessage());
                }
            }
        }

        // 获取调用者IP地址
        String callerIp = request.getRemoteAddr();
        if (logger.isDebugEnabled()) {
            logger.debug("Http POST Request CALLER IP: " + callerIp + "  PARAM: req=" + inputstr);
        }

        if (StringUtils.isBlank(inputstr)) {
            logger.error("无应答内容");
            return null;
        }
        return inputstr;
    }

    /**
     * 从输入的数据流中得到指定的JSON对象<br>
     * 不能取到时返回Null<br>
     * 注意，此方法只能获取以HTTP POST方式提交的请求数据
     *
     * @param request Http请求对象
     *
     * @return JSONObject JSON对象
     */
    public static JSONObject getJsonObjFromRequest(HttpServletRequest request) {
        String inputstr = getStringFromRequest(request);
        if (inputstr == null) {
            return null;
        }
        try {
            return JSONObject.fromObject(inputstr);
        } catch (JSONException jxp) {
            logger.error(jxp.getMessage());
            return null;
        }
    }
}
