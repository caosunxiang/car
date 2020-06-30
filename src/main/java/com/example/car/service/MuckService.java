package com.example.car.service;

import com.example.car.common.utils.json.Body;

public interface MuckService {
    Body selectMuck(String carNo,  String permitNo,String time);

    Body selectMuckByName(String projectName,String time,String name);

    Body selectMuckByProject(String projectId,String time);
}
