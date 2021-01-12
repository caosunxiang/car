package com.example.car.service;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.M03;
import com.example.car.entity.TerminalInfo;

public interface M03Service {
    Body selectM03(String carNumber, String recId, String phone,String MustId);

    Body updateM03(String person, String quality, String dimensions, String scrapTime, String IssuanceDate,
                   String totalQuality, String checkQuality, String tractionQuality, String stopTransport,
                   String stopNumber, String stopEndTime,String RecId, String userid,String stopRemark );

    Body updateStopTransport(String stopTransport,Integer stopNumber,String carid,String stopRemark);

    Body selectM03Status(String MustId);

    Body insertM03(M03 m03, TerminalInfo terminalInfo);
}
