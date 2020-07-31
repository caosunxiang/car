package com.example.car.mapper.mysql;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Map;

/**
 * 设备报警表 Mapper
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 10:52:07
 */
public interface DeviceAlarmMapper {
    List<Map<String, Object>> selectAlarm(@Param("number") String number, @Param("startTime") String startTime,
                                          @Param("endTime") String endTime, @Param("id") Long id,
                                          @Param("size") Integer size, @Param("type") Integer type);

    List<Map<String, Object>> selectAlarmOther(@Param("number") String number, @Param("startTime") String startTime,
                                               @Param("endTime") String endTime, @Param("id") Long id,
                                               @Param("size") Integer size);

    Map<String, Object> selectGpsAlarm(@Param("number") String number);

    Integer selectAlarmCount(@Param("number") String number, @Param("startTime") String startTime,
                             @Param("endTime") String endTime, @Param("id") Long id, @Param("type") Integer type);


    Integer selectAlarmNowCount( @Param("type") Integer type);
}
