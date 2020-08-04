package com.example.car.service;

import com.example.car.common.utils.json.Body;

/**
* 车辆报备登记 Service接口
*
* @author 冷酷的苹果
* @date 2020-07-21 17:28:17
*/
public interface ICarStatusChangeRecordService  {
    Body selectCarStatusRecord(String number,Integer time);
}
