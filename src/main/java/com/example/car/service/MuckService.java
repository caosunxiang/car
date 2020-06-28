package com.example.car.service;

import com.example.car.common.utils.json.Body;

public interface MuckService {
    Body selectMuck(String carNo,  String permitNo);

    Body selectMuckByName(String name,String time);

    Body selectMuckByProject(String projectId,String time);
}
