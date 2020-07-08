package com.example.car.mapper.mysql;

import com.example.car.entity.DeviceAlarmSeverity;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Map;

public interface DeviceAlarmSeverityMapper {
    void insertAlarmSeverity(DeviceAlarmSeverity alarmSeverity);

    List<Map<String, Object>> selectAlarmSeverity(@Param("startTime") String startTime, @Param("number") String number,
                                                  @Param("endTime") String endTime, @Param("name") String name,
                                                  @Param("size") Integer size);

    List<Map<String, Object>> selectAlarmSeverityAll(@Param("startTime") String startTime,
                                                     @Param("endTime") String endTime, @Param("number") String number
            , @Param("size") Integer size);
}
