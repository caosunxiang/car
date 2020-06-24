package com.example.car.mapper.mysql;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Map;

/**
 * 设备报警配置表 Mapper
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 10:51:07
 */
public interface DeviceAlarmSettingsMapper {

    List<Map<String,String>> selectAlarmById(@Param("id")Integer id);
}
