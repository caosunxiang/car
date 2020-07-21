package com.example.car.mapper.mysql;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Map;

/**
 * 设备上下线表 Mapper
 *
 * @author 冷酷的苹果
 * @date 2020-07-21 16:52:53
 */
public interface DeviceOnlineRecordMapper {
    List<Map<String, Object>> selectDeviceOnlineRecord(@Param("name") String name, @Param("status") Integer status,
                                                       @Param("size") Integer size);
}
