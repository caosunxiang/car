package com.example.car.service;

import com.example.car.common.utils.json.Body;

public interface DeviceAlarmSeverityService {
    Body selectAlarmSeverity(String startTime,String number, String endTime,String name,Integer size);
}
