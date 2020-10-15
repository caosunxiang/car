package com.example.car.service;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.M04;
import com.example.car.entity.M07;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MuckService {
    Body selectMuck(String carNo,  String permitNo,String time);

    Body selectMuckByName(String projectName,String time,String name,String endTime,Integer index,Integer size);

    Body selectMuckByProject(String projectId,String time);

    Body selectMuckCount(String projectName,String time,String name,String endTime);

    Body selectCarInfo(String carNo);

    Body selectGivenPlace();

    Body selectConstructionSite();

    Body updateGivenPlace(M07 m07);

    Body updateConstructionSite(M04 m04);

    Body selectCarInfoByCompany(String companyId);
}
