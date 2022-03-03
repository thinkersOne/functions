package org.com.cn.geodesy;

import org.com.cn.geodesy.model.SiteInfoModel;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * SiteInfoModel fromSiteInfo = new SiteInfoModel(28.490295, 105.486654);
 *
 * SiteInfoModel a = new SiteInfoModel(29.490295, 106.486654);
 * SiteInfoModel b = new SiteInfoModel(32.615467, 106.581515);
 * SiteInfoModel c = new SiteInfoModel(25.615467, 104.581515);
 * SiteInfoModel d = new SiteInfoModel(33.615467, 106.581515);
 * SiteInfoModel e = new SiteInfoModel(38.615467, 108.581515);
 * SiteInfoModel f = new SiteInfoModel(32.615467, 106.581515);
 * SiteInfoModel g = new SiteInfoModel(42.615467, 114.581515);
 */
public class CaculateDistanceTest {
    public static void main(String[] args){
        SiteInfoModel fromSiteInfo = new SiteInfoModel(42.615467, 114.581515);
        List<SiteInfoModel> list = new ArrayList<>(100);
//        SiteInfoModel a = new SiteInfoModel(29.490295, 106.486654);
//        SiteInfoModel b = new SiteInfoModel(32.615467, 106.581515);
        SiteInfoModel c = new SiteInfoModel(25.615467, 104.581515);
//        SiteInfoModel d = new SiteInfoModel(33.615467, 106.581515);
//        SiteInfoModel e = new SiteInfoModel(38.615467, 108.581515);
//        SiteInfoModel g = new SiteInfoModel(42.615467, 114.581515);
//        list.add(a);
//        list.add(b);
        list.add(c);
//        list.add(d);
//        list.add(e);
//        list.add(g);

        GlobalCoordinates source = new GlobalCoordinates(fromSiteInfo.getmLatitude(), fromSiteInfo.getmLongitude());
        for (int i = 0; i < list.size(); i++) {
            SiteInfoModel siteInfoModel = list.get(i);
            GlobalCoordinates target = new GlobalCoordinates(siteInfoModel.getmLatitude(), siteInfoModel.getmLongitude());
            double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
            System.out.println("Sphere坐标系计算结果："+meter1 + "米");
        }
    }

    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid){
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        return geoCurve.getEllipsoidalDistance();
    }

}
