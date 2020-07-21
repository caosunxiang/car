package com.example.car.mapper.mysql;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Map;

/**
* 车辆报备登记 Mapper
*
* @author 冷酷的苹果
* @date 2020-07-21 17:28:17
*/
public interface CarStatusChangeRecordMapper  {
    List<Map<String, Object>> selectCarStatusChangeRecord(@Param("name") String name,
                                                       @Param("size") Integer size);
}
