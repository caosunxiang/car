/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: Distance
 * Author:   冷酷的苹果
 * Date:     2020/6/28 9:41
 * Description: 距离测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils;

/**
 * 〈一句话功能简述〉<br>
 * 〈距离测试〉
 *
 * @author 冷酷的苹果
 * @create 2020/6/28
 * @since 1.0.0
 */
public class Distance {
    /**
     * 将两个经纬度坐标转化成距离（米）
     *
     * @param 2个GPS经纬度坐标(latitude1,longitude1)(latitude2,longitude2)
     * @return true:坐标点异常
     * false:坐标点正常
     */
    public static boolean coordinateToDistance(double latitude1, double longitude1, double latitude2,
                                               double longitude2, Double distance) {
        {
            double a = latitude1 * Math.PI / 180.0 - latitude2 * Math.PI / 180.0;
            double b = longitude1 * Math.PI / 180.0 - longitude2 * Math.PI / 180.0;
            double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                    + Math.cos(latitude1 * Math.PI / 180.0)
                    * Math.cos(latitude2 * Math.PI / 180.0)
                    * Math.pow(Math.sin(b / 2), 2)));
            s = s * 6378.137 * 1000;
            s = Math.round(s);
            if (s > distance) {
                return true;
            }
            return false;
        }
    }
}
