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

import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.FileUploadUtils;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.CarInsurance;
import com.example.car.entity.M03;
import com.example.car.entity.OperationLog;
import com.example.car.mapper.sqlserver.CarInsuranceMapper;
import com.example.car.mapper.sqlserver.M03Mapper;
import com.example.car.mapper.sqlserver.OperationLogMapper;
import com.example.car.service.CarInsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private OperationLogMapper operationLogMapper;
    @Autowired
    private M03Mapper m03Mapper;

    @Override
    public Body insertInsurance(String files, String carid, String jqxId, String jqxTime, String jqxCompany,
                                String jqxMoney, String syxId, String syxTime, String syxCompany,
                                String annualVerification, String verification, String examinant,
                                String verificationTime, String userid, String syxMoney, String useRestrict,
                                String carRestrict,
                                String verificationResult, String verificationRemark, String verificationAccessory) {
        String url = null;
        if (!StringUtils.isEmpty(files)) {
            MultipartFile file = FileUploadUtils.base64Convert(files);
            url = FileUploadUtils.fileUpload(file, "img");
        }
        CarInsurance carInsurance = new CarInsurance();
        carInsurance.setCarId(carid);
        carInsurance.setSyxMoney(syxMoney);
        carInsurance.setAnnualVerification(annualVerification);
        carInsurance.setExaminant(examinant);
        carInsurance.setJqxCompany(jqxCompany);
        carInsurance.setJqxId(jqxId);
        carInsurance.setUseRestrict(useRestrict);
        carInsurance.setCarRestrict(carRestrict);
        carInsurance.setJqxMoney(jqxMoney);
        carInsurance.setJqxTime(jqxTime);
        carInsurance.setVerificationResult(verificationResult);
        carInsurance.setVerificationRemark(verificationRemark);
        carInsurance.setVerificationAccessory(verificationAccessory);
        carInsurance.setSyxCompany(syxCompany);
        carInsurance.setSyxId(syxId);
        carInsurance.setSyxTime(syxTime);
        carInsurance.setVerification(verification);
        carInsurance.setInsuranceUrl(url);
        carInsurance.setVerificationTime(verificationTime);
        carInsuranceMapper.insertInsurance(carInsurance);
        OperationLog operationLog = new OperationLog(null, carid, "上传保险合同", "用户上传保险合同",
                DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN), userid, null, null);
        operationLogMapper.insertLog(operationLog);
        return Body.newInstance(carInsurance);
    }

    @Override
    public Body selectInsuranceByCarId(String carid, String MustId, String verificationTime, String name) {
        List<CarInsurance> carInsurances = carInsuranceMapper.selectInsuranceByCarId(carid, MustId, verificationTime,
                name);
        List<CarInsurance> list = new ArrayList<>();
        for (CarInsurance carInsurance : carInsurances) {
            if (!StringUtils.isEmpty(carInsurance.getVerificationTime())) {
                carInsurance.setVerificationTime(DateUtil.changeTime(carInsurance.getVerificationTime(),
                        "yyyy年MM月dd日"));
            }
            if (!StringUtils.isEmpty(carInsurance.getJqxTime())) {
                carInsurance.setJqxTime(DateUtil.changeTime(carInsurance.getJqxTime(),
                        "yyyy年MM月dd日"));
            }
            if (!StringUtils.isEmpty(carInsurance.getSyxTime())) {
                carInsurance.setSyxTime(DateUtil.changeTime(carInsurance.getSyxTime(),
                        "yyyy年MM月dd日"));
            }
            if (!StringUtils.isEmpty(carInsurance.getCarId())) {
                list.add(carInsurance);
            }
        }
        return Body.newInstance(list);
    }

    @Override
    public Body updateInsuranceUrl(String files, Integer insuranceId, String carid, String userid) {
        MultipartFile file = FileUploadUtils.base64Convert(files);
        String url = FileUploadUtils.fileUpload(file, "img");
        CarInsurance carInsurance = new CarInsurance();
        carInsurance.setInsuranceId(insuranceId);
        carInsurance.setInsuranceUrl(url);
        carInsuranceMapper.updateInsuranceUrl(carInsurance);
        OperationLog operationLog = new OperationLog(null, carid, "修改", "修改合同信息", DateUtil.getDateFormat(new Date(),
                DateUtil.FULL_TIME_SPLIT_PATTERN), userid, null, null);
        operationLogMapper.insertLog(operationLog);
        return Body.newInstance(carInsurance);
    }

    @Override
    public Body selectInsuranceTime(String carId) {
        List<String> list = this.carInsuranceMapper.selectInsuranceTime(carId);
        List<String> strings = new ArrayList<>();
        for (String s : list) {
            if (!StringUtils.isEmpty(s) && !s.equals("1") && !s.equals("0")) {
                System.out.println(s);
                strings.add(DateUtil.changeTime(s,"yyyy年MM月dd日"));
            }
        }
        return Body.newInstance(strings);
    }

    @Override
    public Body updateInsurance(CarInsurance carInsurance) {
        if (!StringUtils.isEmpty(carInsurance.getVerificationAccessory())) {
            MultipartFile file = FileUploadUtils.base64Convert(carInsurance.getVerificationAccessory());
            String url = FileUploadUtils.fileUpload(file, "img");
            carInsurance.setVerificationAccessory(url);
        }
        this.carInsuranceMapper.updateInsurance(carInsurance);
        return Body.BODY_200;
    }

    @Override
    public Body synInsurance() {
        List<M03> m03s = m03Mapper.selectM03(null, null, null, null);
        for (M03 m03 : m03s) {
            CarInsurance carInsurance = new CarInsurance(null, null, m03.getM0315(), m03.getM0332(), m03.getM0333(),
                    null, m03.getM0319(), m03.getM0317(), m03.getM0318(), m03.getM0316(), m03.getM0307(),
                    m03.getM0330(), m03.getM0329(), m03.getM0328(), m03.getRecId(), m03.getM0313(), m03.getM0314(),
                    null, null, null, null, "0",null);
            this.carInsuranceMapper.insertInsurance(carInsurance);
        }
        return Body.BODY_200;
    }

    @Override
    public Body deleteVerification(Integer insuranceId) {
        this.carInsuranceMapper.deleteVerification(insuranceId);
        return Body.BODY_200;
    }

    @Override
    public Body selectInsuranceCount(String auditStatus, String MustId) {
        return Body.newInstance(this.carInsuranceMapper.selectInsuranceCount(auditStatus,MustId));
    }

}
