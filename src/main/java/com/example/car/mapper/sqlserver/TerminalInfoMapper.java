package com.example.car.mapper.sqlserver;

import com.example.car.entity.TerminalInfo;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TerminalInfoMapper {
    void insertTerminal(TerminalInfo terminalInfo);

    List<TerminalInfo> selectTerminal(@Param("carNumber") String carNumber, @Param("carId") String carId, @Param(
            "deptid") String deptid);

    void updateTerminal(TerminalInfo terminalInfo);

    void deleteTerminal(@Param("terminalId")Integer terminalId);
}
