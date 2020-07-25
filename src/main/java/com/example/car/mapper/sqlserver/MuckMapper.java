package com.example.car.mapper.sqlserver;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MuckMapper {
    List<Map<String, String>> selectMuck(@Param("carNo") String carNo, @Param("permitNo") String permitNo,@Param("time")String time);

    List<Map<String, String>> selectMuckByName(@Param("projectName") String projectName,@Param("time")String time,@Param("name")String name,
                                               @Param("endTime")String endTime,@Param("page") Integer page,@Param("size")Integer size);

    List<Map<String, String>> selectMuckByProject(@Param("projectId") String projectId,@Param("time")String time);
}
