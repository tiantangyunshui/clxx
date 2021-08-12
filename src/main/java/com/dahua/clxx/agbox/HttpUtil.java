package com.dahua.clxx.agbox;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dahua.clxx.exception.ErrorUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * http post请求 (上传数据)
     */
    public static String doPostValByImgurl(String url, String method, Object paramObject, String key){
        return doPostValByImgurl(url,method,paramObject,null,key);
    }

    /**
     * http post请求 (上传数据+图片)
     */
    public static String doPostValByImgurl(String url, String method, Object paramObject, byte[] imgByte, String key){
        //组装请求参数
        RequestMessage reqMsg = new RequestMessage(method);
        if(paramObject != null) {
            reqMsg.setParams(paramObject);
        }
        String jsonStr = JSON.toJSONString(reqMsg);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建http post
        HttpPost httpPost = new HttpPost(url);
        //设置参数
        MultipartEntityBuilder mub = MultipartEntityBuilder.create();
        mub.addPart("key",new StringBody(key, ContentType.APPLICATION_JSON));
        mub.addPart("json",new StringBody(jsonStr,ContentType.APPLICATION_JSON));
        //如果图片路径不为空则上传图片s
        if(imgByte != null){
            mub.addBinaryBody("snap_pic_dh", imgByte);
        }
        httpPost.setEntity(mub.build());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int statusCode = status.getStatusCode();
            if(statusCode == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                logger.info("添加事件：{}",jsonString);
                return jsonString;
            }else{
                logger.error("http post请求没有正确返回:[{}]",status);
            }
        } catch (Exception e) {
            logger.error("添加事件异常{}，\nparam:{}", ErrorUtil.err(e),jsonStr);
        }finally{
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("关闭response异常:[{}],[{}]",e.getMessage(),e);
                }
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("关闭httpClient异常:[{}],[{}]",e.getMessage(),e);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        String url= "http://172.18.11.100:80/agbox/device/entrance";
//        String method = "addEvent";
//        String pp = "{ \"deviceId\": \"dahua123\", \"channel\": 1, \"certifiedType\": 111, \"certifiedNo\": \"310104197111062042\", " +
//                "\"name\": \"王炸2\", \"phone\": \"139033445566\", \"triggerTime\": \"2021-08-11 03:24:30\", " +
//                "\"eventCode\": 20, \"cardId\": \"182A1FF4\", \"similarity\": 81, \"note\": \"备注\", \"eventPic\": \"snap_pic_dh\"\n" +
//                "}";
//        Object paramObject = JSONObject.parseObject(pp);
//        String imgUrl = "http://172.18.11.242:9876/e77c4eb4-f432-11eb-9fb0-d09466728c87/20210809/1/2ac88044-f8c5-11eb-959a-d09466728c87.jpg";
//        String key = "73824330-f58e-4d82-8b68-cc3f7cbbd865";
//        // 添加出入口事件
//        System.out.println(doPostValByImgurl(url,method,paramObject,getImgbyte(imgUrl),key));

        String url= "http://172.18.11.100:80/agbox/basic";
        String method = "getProvinceCode";
        String pp = "{}";
        Object paramObject = JSONObject.parseObject(pp);
        String key = "73824330-f58e-4d82-8b68-cc3f7cbbd865";
        // 获取省
        System.out.println(doPostValByImgurl(url,method,paramObject,key));
    }

    //根据http路径获取图片流
    public static byte[] getImgbyte(String url) {
        logger.info("ag人员图片{}",url);
        InputStream in;
        HttpURLConnection conn = null;
        URL readurl;
        byte[] buf = new byte[1024];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            readurl = new URL(url);
            conn = (HttpURLConnection) readurl.openConnection();
            conn.connect();
            int resCode = conn.getResponseCode();
            in = conn.getInputStream();
            if(200 == resCode) {
                int len;
                while((len = in.read(buf)) != -1) {
                    out.write(buf,0,len);
                }
                in.close();
            }
        } catch (Exception e) {
            logger.error("获取图片流异常:[{}],[{}]",e.getMessage(),e);
        } finally {
            try {
                out.close();
                if(conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                logger.error("关闭异常:[{}],[{}]",e.getMessage(),e);
            }
        }
        return out.toByteArray();
    }

}
