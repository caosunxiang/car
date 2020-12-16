package com.example.car.service;

import com.example.car.common.utils.json.Body;

public interface CarInsuranceService {
    Body insertInsurance(String files, String carid, String jqxId, String jqxTime, String jqxCompany,
                         String jqxMoney, String syxId, String syxTime, String syxCompany,
                         String annualVerification, String verification, String examinant, String verificationTime);

    Body selectInsuranceByCarId(String carid);

    Body updateInsuranceUrl(String files, Integer insuranceId);
}
