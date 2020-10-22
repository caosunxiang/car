package com.example.car.mapper.sqlserver;


import com.example.car.entity.M04;
import com.example.car.entity.M07;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MuckMapper {
    List<Map<String, String>> selectMuck(@Param("carNo") String carNo, @Param("permitNo") String permitNo,@Param("time")String time);

    List<Map<String, String>> selectMuckByName(@Param("projectName") String projectName,@Param("time")String time,@Param("name")String name,
                                               @Param("endTime")String endTime,@Param("page") Integer page,@Param("size")Integer size);

    List<Map<String, String>> selectMuckByProject(@Param("projectId") String projectId,@Param("time")String time);

    Integer selectMuckCount(@Param("projectName") String projectName,@Param("time")String time,@Param("name")String name,
                            @Param("endTime")String endTime);

    List<Map<String, String>> selectCarInfo(@Param("carNo") String carNo);

    List<M07>selectGivenPlace();

    List<M04>selectConstructionSite();

    M07 selectGivenPlaceOne(@Param("recid") String recid);

    M04 selectConstructionSiteOne(@Param("recid") String recid);

    void updateGivenPlace(M07 m07);

    void updateConstructionSite(M04 m04);

    List<Map<String,Object>> selectCarInfoByCompany(@Param("companyId")String companyId);
}
