package com.example.car.service;

import com.example.car.common.utils.json.Body;

public interface TerminalInfoService {
    Body insertTerminalInfo();

    Body selectTerminal(String deptid, String carId, String carNumber);

    Body insertTerminalInfo(String terminal, String terminalType, String createUser, String
            carId, String deptid, String installstatus, String installtime, String carNumber);

    Body updateTerminal(String modifyUser, String isDelete, String terminal, String terminalType, String carNumber,
                        Integer terminalId,String carid);

    Body deleteTerminal(Integer terminalId);
}
