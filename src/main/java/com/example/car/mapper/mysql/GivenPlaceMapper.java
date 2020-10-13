package com.example.car.mapper.mysql;

import com.example.car.entity.GivenPlace;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface GivenPlaceMapper {
    void insertGivenPlace(GivenPlace givenPlace);

    void updateGivenPlace(GivenPlace givenPlace);

    void delGivenPlace(@Param("id") Integer id);

    GivenPlace selectOne(@Param("id") Integer id);

    List<GivenPlace> selectAll();
}
