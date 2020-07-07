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
    public Body selectAlarmSeverity(String startTime,String number, String endTime,String name) {
        return deviceAlarmSeverityService.selectAlarmSeverity(startTime,number,endTime,name);
    }
}
