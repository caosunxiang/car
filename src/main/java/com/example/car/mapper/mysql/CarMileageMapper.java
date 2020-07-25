package com.example.car.mapper.mysql;

import com.example.car.entity.CarMileage;
import io.lettuce.core.dynamic.annotation.Param;

public interface CarMileageMapper {
    void insertCarMileage(CarMileage carMileage);

    void updateCarMileage(CarMileage carMileage);

    CarMileage selectByName(@Param("name")String name);
}
