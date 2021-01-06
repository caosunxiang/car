package com.example.car.service;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.CarCertification;

public interface CarCertificationService {
    Body selectCarCertification(String carid);


    Body updateCarCertification(String certificationStatus, String certificationTime, String carId
            , String userid);
}
