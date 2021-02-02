package com.example.car.service;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.M03;
import com.example.car.entity.TerminalInfo;

import java.text.ParseException;

public interface M03Service {
    Body selectM03(String carNumber, String recId, String phone,String MustId,String type) ;

    Body updateM03(M03 m03, String userid);

    Body updateStopTransport(String stopTransport,Integer stopNumber,String carid,String stopRemark,String MustId,String
                              userid);

    Body selectM03Status(String MustId,String name,String type) ;

    Body insertM03(M03 m03, TerminalInfo terminalInfo);

    Body deleteM03(String RecId);

    Body selectM03Count(String auditStatus,String MustId);
}
