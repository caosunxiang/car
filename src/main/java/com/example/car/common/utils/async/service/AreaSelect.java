/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: AreaSelect
 * Author:   冷酷的苹果
 * Date:     2020/7/21 9:20
 * Description: 区域查车
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.common.utils.async.service;

import com.example.car.common.utils.Distance;
import com.example.car.entity.HistoricalRoute;
import com.example.car.mapper.mysql.HistoricalRouteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈区域查车〉
 *
 * @author 冷酷的苹果
 * @create 2020/7/21
 * @since 1.0.0
 */
@Service
public class AreaSelect {

    @Autowired
    private HistoricalRouteMapper historicalRouteMapper;

    public boolean areaSelect(Double lat1, Double lng1, String startTime,
                                            String endTime, String number, Double distance) {
        List<HistoricalRoute> list = historicalRouteMapper.selectHistoricalRoute(startTime, endTime, number);
        boolean isArea=false;
        for (HistoricalRoute historicalRoute : list) {
            System.out.println(historicalRoute.toString());
            if ( !Distance.coordinateToDistance(lat1, lng1, Double.parseDouble(historicalRoute.getLat()),
                    Double.parseDouble(historicalRoute.getLng()), distance)){
                isArea=true;
                break;
            }
        }
        return isArea;
    }

    public List<HistoricalRoute> areaSelect(Double lat1, Double lng1, String startTime,
                              String endTime, Double distance) {
        List<HistoricalRoute> numbers=new ArrayList<>();
        List<HistoricalRoute> list = historicalRouteMapper.selectHistoricalRoute(startTime, endTime,null);
        for (HistoricalRoute historicalRoute : list) {
            System.out.println(historicalRoute.toString());
            boolean flag=true;
               if (numbers.size()>0){
                   for (HistoricalRoute number : numbers) {
                       if (number.getCarid().equals(historicalRoute.getCarid())) {
                           flag = false;
                           break;
                       }
                   }
                   if (flag){
                       if ( !Distance.coordinateToDistance(lat1, lng1, Double.parseDouble(historicalRoute.getLat()),
                               Double.parseDouble(historicalRoute.getLng()), distance)){
                           numbers.add(historicalRoute);
                       }
                   }
               }else {
                   if ( !Distance.coordinateToDistance(lat1, lng1, Double.parseDouble(historicalRoute.getLat()),
                           Double.parseDouble(historicalRoute.getLng()), distance)){
                       numbers.add(historicalRoute);
                   }
               }
        }
        return numbers;
    }
}
