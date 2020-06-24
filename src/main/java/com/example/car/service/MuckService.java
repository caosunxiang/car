package com.example.car.service;

import com.example.car.common.utils.json.Body;

public interface MuckService {
    Body selectMuck(String carNo,  String permitNo);

    Body selectMuckByName(String name);

    Body selectMuckByProject(String projectId);
}
