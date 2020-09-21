package com.example.car.mapper.mysql;

import com.example.car.entity.HistoricalRoute;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface HistoricalRouteMapper {
    Integer insertHistoricalRoute(@Param("list") List<HistoricalRoute> list);

    List<HistoricalRoute> selectHistoricalRoute(@Param("startTime") String startTime,
                                                @Param("endTime") String endTime, @Param("name") String name);
}
