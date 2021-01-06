package com.example.car.mapper.sqlserver;

import com.example.car.entity.DriverHistorical;
import com.example.car.entity.DriverInfo;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface DriverInfoMapper {
    void insertDriver(DriverInfo driverInfo);

    void updateDriver(DriverInfo driverInfo);

    List<DriverInfo> selectDriverInfo(@Param("driverId")String driverId);

    void insertDriverHistorical(DriverHistorical driverHistorical);

    List<DriverInfo>selectDriverHistorical(@Param("carId")String carId);
}
