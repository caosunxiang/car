package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.ICarStatusChangeRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 车辆报备登记 Controller
 *
 * @author 冷酷的苹果
 * @date 2020-07-21 17:28:17
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CarStatusChangeRecordController {

    private final ICarStatusChangeRecordService carStatusChangeRecordService;


    /**
     * @Description:查找车辆报备记录
     * @Param: [number, time]
     * @return: com.example.car.common.utils.json.Body
     * @Author: 冷酷的苹果
     * @Date: 2020/8/1 11:06
     */
    @RequestMapping("selectCarStatusRecord")
    public Body selectCarStatusRecord(String number, Integer time) {
        return carStatusChangeRecordService.selectCarStatusRecord(number, time);
    }

}
