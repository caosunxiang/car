package com.example.car.service;

import com.example.car.common.utils.json.Body;

public interface M03Service {
    Body selectM03(String carNumber, String recId, String phone);

    Body updateM03(String person, String quality, String dimensions, String scrapTime, String IssuanceDate,
                   String totalQuality, String checkQuality, String tractionQuality, String stopTransport,
                   String stopNumber, String stopEndTime,String RecId, String userid );
}
