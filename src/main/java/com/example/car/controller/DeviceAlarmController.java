package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.IDeviceAlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备报警表 Controller
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 10:52:07
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class DeviceAlarmController {

    private final IDeviceAlarmService deviceAlarmService;

    @RequestMapping("selectAlarm")
    public Body selectAlarm(String number,String startTime,String endTime,Integer size,Integer type) {
        return this.deviceAlarmService.selectAlarm(number,startTime,endTime,size,type);
    }
}
