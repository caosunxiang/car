package com.example.car.mapper.mysql;

import com.example.car.entity.Place;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface PlaceMapper {
    void insertPlace(Place place);

    void updatePlace(Place place);

    void delPlace(@Param("id") Integer id);

    Place selectOne(@Param("id") Integer id);

    List<Place> selectAll(@Param("name") String name,@Param("page")Integer page,@Param("size")Integer size);
}
