package com.example.car.mapper.sqlserver;

import com.example.car.entity.CarCertification;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CarCertificationMapper {

    List<CarCertification>selectCarCertification(@Param("carid")String carid);

    Integer insertCarCertification(CarCertification carCertification);

    Integer updateCarCertification(CarCertification carCertification);
}
