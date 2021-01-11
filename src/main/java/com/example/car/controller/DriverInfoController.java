/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: DriverInfoController
 * Author:   冷酷的苹果
 * Date:     2020/12/24 15:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.service.DriverInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/24
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class DriverInfoController {

    @Autowired
    private DriverInfoService driverInfoService;

    /**
     * @ Description: 查询驾驶员信息
     * @ Param: [driverId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/24 16:01
     */
    @RequestMapping("selectDriverInfo")
    public Body selectDriverInfo(String driverId,String MustId) {
        return driverInfoService.selectDriverInfo(driverId,MustId);
    }

    /**
     * @ Description: 添加驾驶员信息
     * @ Param: [driverName, driverMobile, driverCardNo, driverReviewTime, driverStatus, driverPhotoUrl, driverSex,
     * driverAddress, files, userid]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/24 16:02
     */
    @RequestMapping("insertDriver")
    public Body insertDriver(String driverName, String driverMobile, String driverCardNo, String driverReviewTime,
                             String driverStatus, String driverFiles, String driverSex, String driverAddress,
                             String files, String carId, String userid) {
        if (StringUtils.isEmpty(files) || StringUtils.isEmpty(driverFiles)) {
            return Body.newInstance(201, "请上传图片");
        }
        return driverInfoService.insertDriver(driverName, driverMobile, driverCardNo, driverReviewTime, driverStatus,
                driverFiles, driverSex, driverAddress, files, carId, userid);
    }


    /**
     * @ Description: 修改驾驶员信息
     * @ Param: [driverId, driverName, driverMobile, driverCardNo, driverReviewTime, driverStatus, driverPhotoUrl,
     * driverSex, driverAddress, files, userid]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/24 16:02
     */
    @RequestMapping("updateDriver")
    public Body updateDriver(Integer driverId, String driverName, String driverMobile, String driverCardNo,
                             String driverReviewTime, String driverStatus, String driverFiles, String driverSex,
                             String driverAddress, String files, String carId, String userid) {
        if (StringUtils.isEmpty(files) || StringUtils.isEmpty(driverFiles)) {
            return Body.newInstance(201, "请上传图片");
        }
        return driverInfoService.updateDriver(driverId, driverName, driverMobile, driverCardNo, driverReviewTime,
                driverStatus, driverFiles, driverSex, driverAddress, files, carId, userid);
    }

    /**
     * @ Description: 查找驾驶员历史信息
     * @ Param: [carId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/25 15:39
     */
    @RequestMapping("selectDriverHistorical")
    public Body selectDriverHistorical(String carId) {
        return driverInfoService.selectDriverHistorical(carId);
    }
}
