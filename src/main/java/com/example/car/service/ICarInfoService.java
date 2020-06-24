package com.example.car.service;

import com.example.car.common.utils.json.Body;

/**
 * 车辆信息表 Service接口
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 08:54:02
 */
public interface ICarInfoService {
    Body selectCar(String carnumber, String terminalId, String sim);

    Body selectCarByDeptName(String deptname);

    Body selectCarDetail(String deptid, String carnumber, String terminalid, String status);
}
