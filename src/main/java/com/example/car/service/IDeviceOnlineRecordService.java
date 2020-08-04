package com.example.car.service;

import com.example.car.common.utils.json.Body;

/**
* 设备上下线表 Service接口
*
* @author 冷酷的苹果
* @date 2020-07-21 16:52:53
*/
public interface IDeviceOnlineRecordService  {

    Body selectOnlineRecord(String number,Integer time);
}
