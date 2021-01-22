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
    public Body selectDriverInfo(String driverId, String MustId, String name, String driverCardNo) {
        return driverInfoService.selectDriverInfo(driverId, MustId, name,driverCardNo);
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
                             String files, String carId, String userid, String deptid) {
        return driverInfoService.insertDriver(driverName, driverMobile, driverCardNo, driverReviewTime, driverStatus,
                driverFiles, driverSex, driverAddress, files, carId, userid, deptid);
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
                             String driverAddress, String files, String carId, String userid, String deptid) {
        return driverInfoService.updateDriver(driverId, driverName, driverMobile, driverCardNo, driverReviewTime,
                driverStatus, driverFiles, driverSex, driverAddress, files, carId, userid, deptid);
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

    /**
     * @ Description: 驾驶员绑定多辆车
     * @ Param: [driverName, driverMobile, driverCardNo, driverReviewTime, driverStatus, driverFile, driverSex,
     * driverAddress, carNumber, userid]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/14 16:29
     */
    @RequestMapping("insertDrivers")
    public Body insertDrivers(String driverName, String driverMobile, String driverCardNo, String driverReviewTime,
                              String driverStatus, String driverFile, String driverSex, String driverAddress,
                              String carNumber, String userid, String deptid) {
        return driverInfoService.insertDrivers(driverName, driverMobile, driverCardNo, driverReviewTime, driverStatus,
                driverFile, driverSex, driverAddress, carNumber, userid, deptid);
    }

    /** 
    * @ Description: 删除驾驶员
    * @ Param: [driverId]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2021/1/15 14:04
    */
    @RequestMapping("delectDriver")
    public Body delectDriver(Integer driverId) {
        return driverInfoService.delectDriver(driverId);
    }

    /** 
    * @ Description: 同步驾驶员信息
    * @ Param: []
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2021/1/18 16:48
    */
    @RequestMapping("synDriver")
    public Body synDriver() {
        return driverInfoService.synDriver();
    }
}
