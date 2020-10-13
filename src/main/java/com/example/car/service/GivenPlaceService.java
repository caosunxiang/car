package com.example.car.service;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.GivenPlace;

public interface GivenPlaceService {
    Body insertGivenPlace(GivenPlace givenPlace);

    Body updateGivenPlace(GivenPlace givenPlace);

    Body delGivenPlace(Integer id);

    Body selectOne(Integer id);

    Body selectAll();
}
