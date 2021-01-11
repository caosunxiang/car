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
import com.example.car.service.CarInsuranceService;
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
                                String verificationTime,String userid) {
        if (StringUtils.isEmpty(files)){
            return  Body.newInstance(201,"请上传base64格式的图片");
        }
        return carInsuranceService.insertInsurance(files, carid, jqxId, jqxTime, jqxCompany, jqxMoney, syxId, syxTime
                , syxCompany, annualVerification, verification, examinant, verificationTime,userid);
    }

    /**
     * @ Description: 查询年审信息
     * @ Param: [carid]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/16 10:10
     */
    @RequestMapping("selectInsuranceByCarId")
    public Body selectInsuranceByCarId(String carid,String MustId) {
        return carInsuranceService.selectInsuranceByCarId(carid,MustId);
    }

    /**
     * @ Description: 上传合同
     * @ Param: [files, insuranceId]
     * @ return: com.example.car.common.utils.json.Body
     * @ Author: 冷酷的苹果
     * @ Date: 2020/12/16 11:15
     */
    @RequestMapping("updateInsuranceUrl")
    public Body updateInsuranceUrl(String files, Integer insuranceId,String carid,String userid) {
        return carInsuranceService.updateInsuranceUrl(files, insuranceId,carid,userid);
    }
}
