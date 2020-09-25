/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: DeviceAlarmSeverityController
 * Author:   冷酷的苹果
 * Date:     2020/7/1 15:57
 * Description: 严重报警
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.DeviceAlarmSeverityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈严重报警〉
 *
 * @author 冷酷的苹果
 * @create 2020/7/1
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class DeviceAlarmSeverityController {


    private final DeviceAlarmSeverityService deviceAlarmSeverityService;

    /*** 
     * @Description: 查询严重报警
     * @Param: [startTime, endTime]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/7/1 18:26
     */
    @RequestMapping("selectAlarmSeverity")
    public Body selectAlarmSeverity(String startTime, String number, String endTime, String name, Integer size) {
        return deviceAlarmSeverityService.selectAlarmSeverity(startTime, number, endTime, name, size);
    }

    /**
     * @Description:
     * @Param: [startTime, endTime]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/7/20 16:14
     */
    @RequestMapping("selectAlarmSeverityCount")
    public Body selectAlarmSeverityCount(String startTime, String endTime) {
        return deviceAlarmSeverityService.selectAlarmSeverityCount(startTime, endTime);
    }

    /**
     * @Description: 查询有效严重报警
     * @Param: [startTime, number, endTime, name]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/7/22 14:13
     */
    @RequestMapping("selectAlarmSeverityValid")
    public Body selectAlarmSeverityValid(String startTime, String number, String endTime, String name) {
        return deviceAlarmSeverityService.selectAlarmSeverityValid(startTime, number, endTime, name);
    }

    /**
     * @Description: 查询准运证报警
     * @Param: [number]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/7/25 14:02
     */
    @RequestMapping("selectAlarmMuck")
    public Body selectAlarmMuck(String number, String name) {
        return deviceAlarmSeverityService.selectAlarmMuck(number, name);
    }

    /**
     * @Description: 小程序查询严重报警新接口
     * @Param: [name, time, type]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/9/22 14:44
     */
    @RequestMapping("selectNewAlarm")
    public Body selectNewAlarm(String name, String time, Integer type, String number) {
        return deviceAlarmSeverityService.selectNewAlarm(name, time, type, number);
    }
}
