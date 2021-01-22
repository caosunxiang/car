package com.example.car.service;

import com.example.car.common.utils.json.Body;

public interface DriverInfoService {
    Body selectDriverInfo(String driverId,String MustId,String name, String driverCardNo);

    Body insertDriver(String driverName,String driverMobile,String driverCardNo,String driverReviewTime,String driverStatus,
                      String driverFile,String driverSex,String driverAddress,String files,String carId,String userid,String deptid);

    Body updateDriver(Integer driverId,String driverName,String driverMobile,String driverCardNo,String driverReviewTime,String driverStatus,
                      String driverFile,String driverSex,String driverAddress,String files,String carId,String userid,String deptid);

    Body selectDriverHistorical(String carId);

    Body insertDrivers(String driverName,String driverMobile,String driverCardNo,String driverReviewTime,String driverStatus,
                      String driverFile,String driverSex,String driverAddress,String carNumber,String userid,String deptid);

    Body delectDriver(Integer driverId);


    Body synDriver();
}
