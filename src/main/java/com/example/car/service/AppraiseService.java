package com.example.car.service;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.Appraise;

public interface AppraiseService {
    Body selectAppraise(String deptid,String appraiseLevel,Integer id);

    Body insertAppraise(Appraise appraise);

    Body updateAppraise(Appraise appraise);

    Body deleteAppraise(Integer id);
}
