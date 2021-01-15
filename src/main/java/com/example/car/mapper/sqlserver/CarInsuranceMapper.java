package com.example.car.mapper.sqlserver;

import com.example.car.entity.CarInsurance;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface CarInsuranceMapper {
    Integer insertInsurance(CarInsurance carInsurance);

    List<CarInsurance> selectInsuranceByCarId(@Param("carid") String carid, @Param("MustId") String MustId, @Param(
            "verificationTime") String verificationTime,@Param("name")String name);

    Integer updateInsuranceUrl(CarInsurance carInsurance);

    List<String> selectInsuranceTime(@Param("carId") String carId);

    void updateInsurance(CarInsurance carInsurance);

}
