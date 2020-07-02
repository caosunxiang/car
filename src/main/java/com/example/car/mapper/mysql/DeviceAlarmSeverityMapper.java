package com.example.car.mapper.mysql;

import com.example.car.entity.DeviceAlarmSeverity;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface DeviceAlarmSeverityMapper {
    Integer insertAlarmSeverity(DeviceAlarmSeverity alarmSeverity);

    List<DeviceAlarmSeverity> selectAlarmSeverity(@Param("startTime")String startTime,@Param("endTime")String endTime);
}
