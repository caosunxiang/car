/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: InArea
 * Author:   冷酷的苹果
 * Date:     2020/7/21 9:26
 * Description: 判断点是否在矩形内
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈判断点是否在矩形内〉
 *
 * @author 冷酷的苹果
 * @create 2020/7/21
 * @since 1.0.0
 */
public class InArea {
    /**
     *      *
     *      * @param latitue 待测点的纬度
     *      * @param longitude 待测点的经度
     *      * @param areaLatitude1 纬度范围限制1
     *      * @param areaLatitude2 纬度范围限制2
     *      * @param areaLongitude1 经度限制范围1
     *      * @param areaLongitude2 经度范围限制2
     *      * @return
     *     
     */
    public static boolean isInArea(double latitue, double longitude, double areaLatitude1, double areaLatitude2,
                                   double areaLongitude1, double areaLongitude2) {
        if (isInRange(latitue, areaLatitude1, areaLatitude2)) {//如果在纬度的范围内
            if (areaLongitude1 * areaLongitude2 > 0) {//如果都在东半球或者都在西半球
                if (isInRange(longitude, areaLongitude1, areaLongitude2)) {
                    return true;
                } else {
                    return false;
                }
            } else {//如果一个在东半球，一个在西半球
                if (Math.abs(areaLongitude1) + Math.abs(areaLongitude2) < 180) {//如果跨越0度经线在半圆的范围内
                    if (isInRange(longitude, areaLongitude1, areaLongitude2)) {
                        return true;
                    } else {
                        return false;
                    }
                } else {//如果跨越180度经线在半圆范围内
                    double left = Math.max(areaLongitude1, areaLongitude2);//东半球的经度范围left-180
                    double right = Math.min(areaLongitude1, areaLongitude2);//西半球的经度范围right-（-180）
                    if (isInRange(longitude, left, 180) || isInRange(longitude, right, -180)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
    }


    public static boolean isInRange(double point, double left, double right) {
        if (point >= Math.min(left, right) && point <= Math.max(left, right)) {
            return true;
        } else {
            return false;
        }

    }
}
