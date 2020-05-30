package org.com.cn.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * post请求 返回data 对象body实体
     * @return
     */
    public <T> T post(String jsonParam, String url,Class<T> tClass){
        if(StringUtils.isEmpty(jsonParam)){
            return null;
        }
        ResponseEntity<T> stringResponseEntity = null;
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<>(jsonParam,requestHeaders);
            stringResponseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,tClass);
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

}
