package com.example.car.service;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.M04;
import com.example.car.entity.M07;

public interface MuckService {
    Body selectMuck(String carNo, String permitNo, String time);

    Body selectMuckByName(String projectName, String time, String name, String endTime, Integer index, Integer size);

    Body selectMuckByProject(String projectId, String time);

    Body selectMuckCount(String projectName, String time, String name, String endTime);

    Body selectCarInfo(String carNo, Integer size, Integer index, String CompanyName,
                       String DriverName, String EngineNo,
                       String CarframeNo, String CarType);

    Body selectGivenPlace(String name, Integer size, Integer index, String M0702, String M0707, String M0708,
                          String M0703, String M0704, String M0709,
                          String M0710);

    Body selectConstructionSite(String name, Integer size, Integer index, String M0417,
                                String M0419, String M0402,
                                String M0403, String M0414,
                                String M0420, String M0425,
                                String M0426);

    Body updateGivenPlace(M07 m07);

    Body updateConstructionSite(M04 m04);

    Body selectCarInfoByCompany(String companyId);

    Body selectGivenPlaceOne(String recid);

    Body selectConstructionSiteOne(String recid);

    Body selectoperprogress(String number);

    Body selectNotice();

    Body selectM01(String name);

    Body selectCountByMuck(String time);

    Body selectMuckAdvanced(String name, Integer size,Integer index, String BeginTime, String type);

    Body selectMuckPage(String name, Integer size,Integer index, String BeginTime, String type);

    Body PowerControl(String userid);

    Body updateM04VideoUrl(String videoUrl,String videoName,String videoPassword,String recId);

    Body validM04(Integer valid,String RecId);

    Body validM07(Integer valid,String RecId);

    Body selectUserRole(String UserId,String RoleId);

    Body selectApply(String userid);
}
