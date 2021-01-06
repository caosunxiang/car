/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: M03Controller
 * Author:   冷酷的苹果
 * Date:     2020/12/22 18:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.M03Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/22
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class M03Controller {

    @Autowired
    private M03Service m03Service;

    /** 
    * @ Description: 查找车辆信息
    * @ Param: [carNumber, recId, phone]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2020/12/23 14:13
    */
    @RequestMapping("selectM03")
    public Body selectM03(String carNumber, String recId, String phone,String MustId) {
        return m03Service.selectM03(carNumber, recId, phone,MustId);
    }

    /** 
    * @ Description: 修改车辆信息
    * @ Param: [person, quality, dimensions, scrapTime, IssuanceDate, totalQuality, checkQuality, tractionQuality, stopTransport, stopNumber, stopEndTime, RecId, userid]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2020/12/23 14:14
    */
    @RequestMapping("updateM03")
    public Body updateM03(String person, String quality, String dimensions, String scrapTime, String IssuanceDate,
                          String totalQuality, String checkQuality, String tractionQuality, String stopTransport,
                          String stopNumber, String stopEndTime, String RecId, String userid) {
        return m03Service.updateM03(person, quality, dimensions, scrapTime, IssuanceDate, totalQuality, checkQuality,
                tractionQuality, stopTransport, stopNumber, stopEndTime, RecId,userid);
    }

    /** 
    * @ Description:停运
    * @ Param: [stopTransport, stopNumber, stopEndTime, recId]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2020/12/28 10:42
    */
    @RequestMapping("updateStopTransport")
    public Body updateStopTransport(String stopTransport, Integer stopNumber, String recId) {
        return m03Service.updateStopTransport(stopTransport, stopNumber, recId);
    }
}
