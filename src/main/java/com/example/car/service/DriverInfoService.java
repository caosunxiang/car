package com.example.car.service;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.DriverInfo;

public interface DriverInfoService {
    Body selectDriverInfo(String driverId,String MustId,String name, String driverCardNo);

    Body insertDriver(DriverInfo driverInfo, String userid);

    Body updateDriver(DriverInfo driverInfo,String userid);

    Body selectDriverHistorical(String carId);

    Body insertDrivers(DriverInfo driverInfo,String carNumber,String userid);

    Body delectDriver(Integer driverId);


    Body synDriver();

    Body selectDriverCount(String driverStatus, String deptid);

    Body selectDriver(String driverId,String MustId,String name, String driverCardNo);
}
