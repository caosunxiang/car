package com.example.car.mapper.sqlserver;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MuckMapper {
    List<Map<String, String>> selectMuck(@Param("carNo") String carNo, @Param("permitNo") String permitNo);

    List<Map<String, String>> selectMuckByName(@Param("projectName") String name);

    List<Map<String, String>> selectMuckByProject(@Param("projectId") String projectId);
}
