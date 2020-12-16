package com.example.car.mapper.sqlserver;

import com.example.car.entity.CarInsurance;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CarInsuranceMapper {
    Integer insertInsurance(CarInsurance carInsurance);

    List<CarInsurance> selectInsuranceByCarId(@Param("carid")String carid);

    Integer updateInsuranceUrl(CarInsurance carInsurance);
}
