package com.example.car.mapper.mysql;

import com.example.car.entity.CarInfo;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Map;

/**
 * 车辆信息表 Mapper
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 08:54:02
 */
public interface CarInfoMapper {
    List<Map<String, Object>> selectCar(@Param("carnumber") String carnumber, @Param("terminalId") String terminalId,
                                        @Param("sim") String sim, @Param("deptid") long deptid);

    List<Map<String, Object>> selectCarByDeptName(@Param("deptname") String deptname);

    List<Map<String, Object>> selectCarDetail(@Param("deptid") String deptid, @Param("carnumber") String carnumber,
                                              @Param("terminalid") String terminalid, @Param("status") String status);

    CarInfo selectCarOnly(@Param("number") String number);

    Integer selectStatusCount(@Param("id") String id, @Param("status") String status);

    List<Map<String, Object>> selectCarDetailPage(@Param("deptid") String deptid, @Param("carnumber") String carnumber,
                                                  @Param("terminalid") String terminalid,
                                                  @Param("status") String status,
                                                  @Param("index") Integer index, @Param("size") Integer size);
}

