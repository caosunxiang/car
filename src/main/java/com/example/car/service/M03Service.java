package com.example.car.service;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.M03;
import com.example.car.entity.TerminalInfo;

public interface M03Service {
    Body selectM03(String carNumber, String recId, String phone,String MustId);

    Body updateM03(String person, String quality, String dimensions, String scrapTime, String IssuanceDate,
                   String totalQuality, String checkQuality, String tractionQuality, String M0305,String M0306,
                   String fileNumber, String M0304, String RecId, String userid,String M0303,String DLicenseImage,
                   String registration);

    Body updateStopTransport(String stopTransport,Integer stopNumber,String carid,String stopRemark,String MustId);

    Body selectM03Status(String MustId,String name);

    Body insertM03(M03 m03, TerminalInfo terminalInfo);

    Body deleteM03(String RecId);
}
