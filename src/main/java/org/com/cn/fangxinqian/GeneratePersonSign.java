package org.com.cn.fangxinqian;

import org.com.cn.fangxinqian.response.GenerateSignResponse;
import org.com.cn.utils.PostUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成个人签章
 */
public class GeneratePersonSign {
    private static String url = "https://api.fangxinqian.cn/free/personal/signature";

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("phone","15000936409");
        map.put("fontText","李志豪");
        map.put("colorTag",1);
        map.put("fontFamilyTag",1);
        map.put("shape",1);
        GenerateSignResponse response = PostUtils.post(map, url, GenerateSignResponse.class);
        System.out.println(response);
    }



}
