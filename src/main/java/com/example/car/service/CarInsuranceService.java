package com.example.car.service;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.CarInsurance;

import java.text.ParseException;

public interface CarInsuranceService {
    Body insertInsurance(String files, String carid, String jqxId, String jqxTime, String jqxCompany,
                         String jqxMoney, String syxId, String syxTime, String syxCompany,
                         String annualVerification, String verification, String examinant, String verificationTime,
                         String userid,String syxMoney,String useRestrict, String carRestrict,
                         String verificationResult,String verificationRemark,String verificationAccessory);

    Body selectInsuranceByCarId(String carid,String MustId,String verificationTime,String name,String type);

    Body updateInsuranceUrl(String files, Integer insuranceId,String carid,String userid);

    Body selectInsuranceTime(String carId) ;

    Body updateInsurance(CarInsurance carInsurance);

    Body synInsurance();

    Body deleteVerification(Integer insuranceId);

    Body selectInsuranceCount(String auditStatus,String MustId);

    Body selectInsuranceByCarId(String carid);
}
