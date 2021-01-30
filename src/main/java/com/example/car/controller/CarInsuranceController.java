/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: CarInsuranceController
 * Author:   冷酷的苹果
 * Date:     2020/12/15 11:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.controller;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.CarInsurance;
import com.example.car.service.CarInsuranceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/15
 * @since 1.0.0
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CarInsuranceController {

    @Autowired
    private CarInsuranceService carInsuranceService;

    /**
     * @ Description: 添加年审信息
     * @ Param: [files, carid, name, startTime, endTime]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/16 10:10
     */
    @RequestMapping("insertInsurance")
    public Body insertInsurance(String files, String carid, String jqxId, String jqxTime, String jqxCompany,
                                String jqxMoney, String syxId, String syxTime, String syxCompany,
                                String annualVerification, String verification, String examinant,
                                String verificationTime, String userid, String syxMoney, String useRestrict,
                                String carRestrict, String verificationResult, String verificationRemark,
                                String verificationAccessory) {
        return carInsuranceService.insertInsurance(files, carid, jqxId, jqxTime, jqxCompany, jqxMoney, syxId, syxTime
                , syxCompany, annualVerification, verification, examinant, verificationTime, userid, syxMoney,
                useRestrict, carRestrict, verificationResult, verificationRemark, verificationAccessory);
    }

    /**
     * @ Description: 查询年审信息
     * @ Param: [carid]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/16 10:10
     */
    @RequestMapping("selectInsuranceByCarId")
    public Body selectInsuranceByCarId(String carid, String MustId, String verificationTime, String name) throws ParseException {
        return carInsuranceService.selectInsuranceByCarId(carid, MustId, verificationTime, name);
    }

    /**
     * @ Description: 上传合同
     * @ Param: [files, insuranceId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/16 11:15
     */
    @RequestMapping("updateInsuranceUrl")
    public Body updateInsuranceUrl(String files, Integer insuranceId, String carid, String userid) {
        return carInsuranceService.updateInsuranceUrl(files, insuranceId, carid, userid);
    }

    /**
     * @ Description: 查询年审日期
     * @ Param: []
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/13 9:12
     */
    @RequestMapping("selectInsuranceTime")
    public Body selectInsuranceTime(String carId) throws ParseException {
        return carInsuranceService.selectInsuranceTime(carId);
    }

    /**
     * @ Description: 修改年审信息
     * @ Param: [carInsurance]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/14 15:45
     */
    @RequestMapping("updateInsurance")
    public Body updateInsurance(CarInsurance carInsurance) {
        if (StringUtils.isEmpty(carInsurance.getCarId())) {
            return Body.BODY_451;
        }
        return this.carInsuranceService.updateInsurance(carInsurance);
    }

    /**
     * @ Description: 同步保险信息
     * @ Param: []
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/14 17:44
     */
    @RequestMapping("synInsurance")
    public Body synInsurance() {
        return this.carInsuranceService.synInsurance();
    }


    /**
     * @ Description: 清空年审记录
     * @ Param: [insuranceId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2021/1/21 15:01
     */
    @RequestMapping("deleteVerification")
    public Body deleteVerification(Integer insuranceId) {
        return this.carInsuranceService.deleteVerification(insuranceId);
    }

    /** 
    * @ Description: 查找不同状态的保险信息
    * @ Param: [auditStatus, MustId]
    * @ return: com.example.car.common.utils.json.Body
    * @ Author: 冷酷的苹果
    * @ Date: 2021/1/27 18:15
    */
    @RequestMapping("selectInsuranceCount")
    public Body selectInsuranceCount(String auditStatus, String MustId) {
return this.carInsuranceService.selectInsuranceCount(auditStatus,MustId);
    }
}


