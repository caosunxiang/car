package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.IDeviceOnlineRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备上下线表 Controller
 *
 * @author 冷酷的苹果
 * @date 2020-07-21 16:52:53
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class DeviceOnlineRecordController {

    private final IDeviceOnlineRecordService deviceOnlineRecordService;

    /** 
    * @Description: 查询车辆上下线记录
    * @Param: [number, time]
    * @return: com.example.car.common.utils.json.Body
    * @Author: 冷酷的苹果
    * @Date: 2020/8/1 10:45
    */
    @RequestMapping("selectOnlineRecord")
    public Body selectOnlineRecord(String number, Integer time) {
       return deviceOnlineRecordService.selectOnlineRecord(number,time);
    }
}
