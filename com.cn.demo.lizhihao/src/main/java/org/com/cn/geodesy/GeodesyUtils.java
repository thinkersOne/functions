package org.com.cn.geodesy;

import com.alibaba.fastjson.JSON;
import org.com.cn.geodesy.model.LineInfoModel;
import org.com.cn.geodesy.model.SiteInfoModel;
import org.com.cn.utils.ListUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeodesyUtils {

    /**
     * 计算两点之间距离差
     * @param a
     * @param b
     * @return
     */
    public static double calculateTwoPointsDiff(SiteInfoModel a, SiteInfoModel b){
        if(a == null || b == null){
            return 0.00d;
        }
        double latitudeDiff = b.getmLatitude() - a.getmLatitude();
        double longitudeDiff = b.getmLongitude() - a.getmLongitude();
        double result = Math.sqrt(latitudeDiff * latitudeDiff + longitudeDiff * longitudeDiff);
        return result;
    }

    /**
     * 计算 多点之间的距离 取出所有最小值
     * 返回：按照顺序排列的 站点
     * @param list
     * @return
     */
    public static LineInfoModel calculateListPointsDiff(SiteInfoModel fromSiteInfo,List<SiteInfoModel> list,
        List<Double> result,List<SiteInfoModel> siteInfoModels){
        if(!ListUtils.anyList(list) || fromSiteInfo == null){
            return null;
        }
        if(result == null){
            result = new ArrayList<>(20);
        }
        if(siteInfoModels == null){
            siteInfoModels = new ArrayList<>(50);
        }
        siteInfoModels.add(fromSiteInfo);
        double minPoint = 0;
        int pointIndex = 0;//记录最后匹配到 的最小点的位置
        for (int i = 0; i < list.size(); i++) {
            double toPoint = calculateTwoPointsDiff(fromSiteInfo, list.get(i));
            if(minPoint == 0 || (minPoint > toPoint)){
                minPoint = toPoint;
                pointIndex = i;
            }
        }
        //记录
        result.add(minPoint);
        fromSiteInfo = list.get(pointIndex);
        list.remove(pointIndex);
        if(ListUtils.anyList(list)){
            calculateListPointsDiff(fromSiteInfo,list,result,siteInfoModels);
        }else{
            siteInfoModels.add(fromSiteInfo);
        }
        return new LineInfoModel(result, siteInfoModels);
    }

    /**
     * 将所有 的站点统一放入list集合中，默认 第一个为 始发地
     * @param list
     * @return
     */
    public static LineInfoModel calculateListPointsDiff(List<SiteInfoModel> list){
        if(!ListUtils.anyList(list)){
            return null;
        }
        SiteInfoModel fromSiteInfo = list.get(0);
        list.remove(0);
        LineInfoModel lineInfoModel = calculateListPointsDiff(fromSiteInfo, list, null, null);
        if(lineInfoModel == null){
            return null;
        }
        List<SiteInfoModel> siteInfoModels = lineInfoModel.getSiteInfoModels();
        //总的 订单数 为 所有的 站点数 - 1  (去掉始发地)
        lineInfoModel.setOrderTotalCount(siteInfoModels.size() - 1);
        long productTotal = siteInfoModels.stream().filter(v-> v.getProductCount() != null)
                .collect(Collectors.summarizingInt(SiteInfoModel::getProductCount)).getSum();
        lineInfoModel.setProductTotalCount(productTotal);
        return lineInfoModel;
    }

    public static void main(String[] args) {
        SiteInfoModel fromSiteInfo = new SiteInfoModel(28.490295, 105.486654);
        List<SiteInfoModel> list = new ArrayList<>(100);
        SiteInfoModel a = new SiteInfoModel(29.490295, 106.486654,200);
        SiteInfoModel b = new SiteInfoModel(32.615467, 106.581515,100);
        SiteInfoModel c = new SiteInfoModel(25.615467, 104.581515,20);
        SiteInfoModel d = new SiteInfoModel(33.615467, 106.581515,10);
        SiteInfoModel e = new SiteInfoModel(38.615467, 108.581515,30);
        SiteInfoModel g = new SiteInfoModel(42.615467, 114.581515,50);
        list.add(fromSiteInfo);
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);
        list.add(g);

        LineInfoModel lineInfoModel = calculateListPointsDiff(list);
        System.out.println(JSON.toJSON(lineInfoModel));
    }


}
