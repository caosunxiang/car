package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.IDeviceAlarmSettingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备报警配置表 Controller
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 10:51:07
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class DeviceAlarmSettingsController {

    private final IDeviceAlarmSettingsService deviceAlarmSettingsService;

    /*** 
    * @Description: 查询报警信息类型
    * @Param: [id]
    * @return: com.example.car.common.utils.json.Body
    * @Author: 冷酷的苹果
    * @Date: 2020/6/28 8:37
    */
    @RequestMapping("selectAlarmById")
    public Body selectAlarmById(Integer id) {
        return this.deviceAlarmSettingsService.selectAlarmById(id);
    }
}
