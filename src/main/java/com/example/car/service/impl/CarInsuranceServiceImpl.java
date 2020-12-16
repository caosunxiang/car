/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: CarInsuranceServiceImpl
 * Author:   冷酷的苹果
 * Date:     2020/12/15 10:48
 * Description: 车辆保险
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.FileUploadUtils;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.CarInsurance;
import com.example.car.mapper.sqlserver.CarInsuranceMapper;
import com.example.car.service.CarInsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈车辆保险〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/15
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "otherTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
public class CarInsuranceServiceImpl implements CarInsuranceService {

    @Autowired
    private CarInsuranceMapper carInsuranceMapper;

    @Override
    public Body insertInsurance(String files, String carid, String jqxId, String jqxTime, String jqxCompany,
                                String jqxMoney, String syxId, String syxTime, String syxCompany,
                                String annualVerification, String verification, String examinant, String verificationTime) {
        MultipartFile file = FileUploadUtils.base64Convert(files);
        String url = FileUploadUtils.fileUpload(file, "img");
        CarInsurance carInsurance=new CarInsurance();
        carInsurance.setCarId(carid);
        carInsurance.setAnnualVerification(annualVerification);
        carInsurance.setExaminant(examinant);
        carInsurance.setJqxCompany(jqxCompany);
        carInsurance.setJqxId(jqxId);
        carInsurance.setJqxMoney(jqxMoney);
        carInsurance.setJqxTime(jqxTime);
        carInsurance.setSyxCompany(syxCompany);
        carInsurance.setSyxId(syxId);
        carInsurance.setSyxTime(syxTime);
        carInsurance.setVerification(verification);
        carInsurance.setInsuranceUrl(url);
        carInsurance.setVerificationTime(verificationTime);
        carInsuranceMapper.insertInsurance(carInsurance);
        return Body.BODY_200;
    }

    @Override
    public Body selectInsuranceByCarId(String carid) {
        List<CarInsurance> carInsurances=carInsuranceMapper.selectInsuranceByCarId(carid);
        return Body.newInstance(carInsurances);
    }

    @Override
    public Body updateInsuranceUrl(String files, Integer insuranceId) {
        MultipartFile file = FileUploadUtils.base64Convert(files);
        String url = FileUploadUtils.fileUpload(file, "img");
        CarInsurance carInsurance=new CarInsurance();
        carInsurance.setInsuranceId(insuranceId);
        carInsurance.setInsuranceUrl(url);
        return Body.BODY_200;
    }
}
