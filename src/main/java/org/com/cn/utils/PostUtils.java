package org.com.cn.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

public class PostUtils {
    static RestTemplate restTemplate = new RestTemplate();
    static{
        restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());
    }

    public String getEntityString(String url,String jsonParam){
        HttpPost post = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            // 设置超时时间
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
            post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Connection", "Close");
            // 构建消息实体
            StringEntity entity = new StringEntity(jsonParam, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);
            HttpResponse response = httpClient.execute(post);
            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            int retCode = 0;
            String sessendId = "";
            // 返回码中包含retCode及会话Id
            for(Header header : response.getAllHeaders()){
                if(header.getName().equals("retcode")){
                    retCode = Integer.parseInt(header.getValue());
                }
                if(header.getName().equals("SessionId")){
                    sessendId = header.getValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(post != null){
                try {
                    post.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * post请求 返回data 对象body实体
     * @return
     */
    public static <T> T  post(Map<String,Object> map, String url,Class<T> tClass){
        if(map == null){
            return null;
        }
        ResponseEntity<T> stringResponseEntity = null;
        try {
            String paramJson = JSON.toJSONString(map);
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<>(paramJson,requestHeaders);
            stringResponseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, tClass);
            if(stringResponseEntity.getStatusCode().value() == 200){
                T body = stringResponseEntity.getBody();
                return body;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    public static void main(String[] args) {

    }

}
