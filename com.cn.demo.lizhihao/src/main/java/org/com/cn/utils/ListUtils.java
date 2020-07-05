package org.com.cn.utils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static List<String> parseList(String source, String regex) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        List<String> strList = new ArrayList<String>();
        if (StringUtils.isEmpty(regex)) {
            strList.add(source);
        } else {
            String[] strArr = source.split(regex);
            for (String str : strArr) {
                if (!StringUtils.isEmpty(str)) {
                    strList.add(str); }
            }
        } return strList;
    }

    public static <T> boolean anyList(List<T> list){
        return list != null && list.size() > 0;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(20);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        System.out.println(list);
        list.remove(0);
        System.out.println(list);

    }


}
