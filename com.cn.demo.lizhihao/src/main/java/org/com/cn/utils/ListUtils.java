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
}
