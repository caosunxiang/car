package com.example.car.service;

import com.example.car.common.utils.json.Body;

public interface DriverInfoService {
    Body selectDriverInfo(String driverId,String MustId);

    Body insertDriver(String driverName,String driverMobile,String driverCardNo,String driverReviewTime,String driverStatus,
                      String driverFile,String driverSex,String driverAddress,String files,String carId,String userid);

    Body updateDriver(Integer driverId,String driverName,String driverMobile,String driverCardNo,String driverReviewTime,String driverStatus,
                      String driverFile,String driverSex,String driverAddress,String files,String carId,String userid);

    Body selectDriverHistorical(String carId);
}
