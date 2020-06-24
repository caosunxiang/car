package com.example.car.service;

import com.example.car.common.utils.json.Body;

/**
 * 设备报警配置表 Service接口
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 10:51:07
 */
public interface IDeviceAlarmSettingsService {
    Body selectAlarmById(Integer id);
}
