package com.example.car.service;

import com.example.car.common.utils.json.Body;

/**
 * 设备报警表 Service接口
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 10:52:07
 */
public interface IDeviceAlarmService {
    Body selectAlarm(String number,String startTime,String endTime,Integer size,Integer type);

    Body selectAlarmOther(String number,String startTime,String endTime,Integer size);
}
