package com.example.car.service;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.Place;
import io.lettuce.core.dynamic.annotation.Param;

public interface PlaceService {
    Body insertPlace(Place place);

    Body updatePlace(Place place);

    Body delPlace(Integer id);

    Body selectOne(Integer id);

    Body selectAll(String name,Integer index,Integer size);
}
