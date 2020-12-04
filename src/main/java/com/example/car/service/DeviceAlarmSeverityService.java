package com.example.car.service;

import com.example.car.common.utils.json.Body;

public interface DeviceAlarmSeverityService {
    Body selectAlarmSeverity(String startTime,String number, String endTime,String name,Integer size);

    Body selectAlarmSeverityCount(String startTime,String endTime);

    Body selectAlarmSeverityValid(String startTime,String number, String endTime,String name);

    Body selectAlarmMuck(String number,String name);

    Body selectNewAlarm(String name,String time,Integer type,String number);

    Body selectNewAlarmPage(String name,String time,Integer type,String number,Integer index,Integer size);

    Body selectCountEveryCar();
}
