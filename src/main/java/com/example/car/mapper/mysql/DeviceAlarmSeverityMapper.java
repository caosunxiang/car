package com.example.car.mapper.mysql;

import com.example.car.common.utils.entity.EChatBean3;
import com.example.car.entity.DeviceAlarmSeverity;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DeviceAlarmSeverityMapper {
    void insertAlarmSeverity(DeviceAlarmSeverity alarmSeverity);

    List<Map<String, Object>> selectAlarmSeverity(@Param("startTime") String startTime, @Param("number") String number,
                                                  @Param("endTime") String endTime, @Param("name") String name,
                                                  @Param("size") Integer size);

    List<Map<String, Object>> selectAlarmSeverityAll(@Param("startTime") String startTime,
                                                     @Param("endTime") String endTime, @Param("number") String number
            , @Param("size") Integer size);

    Integer selectAlarmSeverityCount(@Param("startTime") String startTime,
                                     @Param("endTime") String endTime, @Param("name") String name);

    DeviceAlarmSeverity selectAlarmSeverityTask(@Param("startTime") String startTime, @Param("number") String number,
                                                @Param("endTime") String endTime, @Param("name") String name, @Param(
            "day") String day);

    void updateAlarmSeverity(DeviceAlarmSeverity alarmSeverity);

    List<DeviceAlarmSeverity> selectAlarmSeverityValid(@Param("startTime") String startTime,
                                                       @Param("number") String number,
                                                       @Param("endTime") String endTime, @Param("name") String name);

    List<DeviceAlarmSeverity> selectAlarmMuck(@Param("number") String number, @Param("name") String name);

    Integer selectAlarmNowCount();

    List<String> selectAlarmName();

    Set<String> selectAlarmCar(@Param("deptid") long deptid, @Param("number") String number);

    List<String> selectEChat(@Param("deptid") long deptid, @Param("number") String number);

    List<EChatBean3> selectEChat1(@Param("number") String number, @Param("time") Integer time,
                                  @Param("type") String type, @Param("deptid") String deptid,
                                  @Param("day") String day, @Param("name") String name);

    List<DeviceAlarmSeverity> selectAlarmGroupCar();

}
