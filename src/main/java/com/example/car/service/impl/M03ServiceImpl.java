/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: M03ServiceImp
 * Author:   冷酷的苹果
 * Date:     2020/12/22 18:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.entity.CarStatus;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.DeviceLasposition;
import com.example.car.entity.M03;
import com.example.car.entity.OperationLog;
import com.example.car.entity.TerminalInfo;
import com.example.car.mapper.mysql.DeviceLaspositionMapper;
import com.example.car.mapper.sqlserver.M03Mapper;
import com.example.car.mapper.sqlserver.OperationLogMapper;
import com.example.car.mapper.sqlserver.TerminalInfoMapper;
import com.example.car.service.M03Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/12/22
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "otherTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
public class M03ServiceImpl implements M03Service {


    private final M03Mapper m03Mapper;

    private final OperationLogMapper operationLogMapper;

    private final DeviceLaspositionMapper deviceLaspositionMapper;

    private final TerminalInfoMapper terminalInfoMapper;


    @Override
    public Body selectM03(String carNumber, String recId, String phone, String MustId) {
        List<M03> m03s = m03Mapper.selectM03(carNumber, recId, phone, MustId);
        return Body.newInstance(m03s);
    }

    @Override
    public Body updateM03(String person, String quality, String dimensions, String scrapTime, String IssuanceDate,
                          String totalQuality, String checkQuality, String tractionQuality, String stopTransport,
                          String stopNumber, String stopEndTime, String RecId, String userid) {
        M03 m03 = new M03();
        m03.setPerson(person);
        m03.setQuality(quality);
        m03.setDimensions(dimensions);
        m03.setScrapTime(scrapTime);
        m03.setIssuanceDate(IssuanceDate);
        m03.setTotalQuality(totalQuality);
        m03.setCheckQuality(checkQuality);
        m03.setTractionQuality(tractionQuality);
        m03.setStopTransport(stopTransport);
        m03.setStopNumber(stopNumber);
        m03.setStopEndTime(stopEndTime);
        m03.setRecId(RecId);
        m03Mapper.updateM03(m03);
        OperationLog operationLog = new OperationLog(null, RecId, "修改", "修改车辆信息", DateUtil.getDateFormat(new Date(),
                DateUtil.FULL_TIME_SPLIT_PATTERN), userid, null, null);
        operationLogMapper.insertLog(operationLog);
        return Body.BODY_200;
    }

    @Override
    public Body updateStopTransport(String stopTransport, Integer stopNumber, String recId) {
        m03Mapper.updateStopTransport(stopTransport, stopNumber,
                DateUtil.severalDaysAgo(DateUtil.FULL_TIME_SPLIT_PATTERN, -stopNumber), recId);
        return Body.BODY_200;
    }

    @Override
    public Body selectM03Status(String MustId) {
        List<CarStatus> m03s = m03Mapper.selectM03Status(MustId);
        if (m03s.size() > 0) {
            for (CarStatus m03 : m03s) {
                DeviceLasposition deviceLasposition = deviceLaspositionMapper.selectLaspositionByCarNo(m03.getM0331());
                if (StringUtils.isEmpty(deviceLasposition)) {
                    m03.setStatus("");
                } else {
                    m03.setStatus(deviceLasposition.getCarstatus().toString());
                }
            }
        }
        return Body.newInstance(m03s);
    }

    @Override
    public Body insertM03(M03 m03, TerminalInfo terminalInfo) {
        String recId= UUID.randomUUID().toString();
        m03.setRecId(recId);
        this.m03Mapper.insertM03(m03);
        terminalInfo.setCarNumber(m03.getM0331());
        terminalInfo.setCreateDate(DateUtil.getDateFormat(new Date(),DateUtil.FULL_TIME_SPLIT_PATTERN));
        terminalInfo.setCreateUser(m03.getCreator().toString());
        terminalInfo.setInstallstatus("0");
        terminalInfo.setInstalltime(DateUtil.getDateFormat(new Date(),DateUtil.FULL_TIME_SPLIT_PATTERN));
        terminalInfo.setIsDelete("0");
        terminalInfo.setCarId(recId);
        terminalInfo.setDeptid(m03.getMustId());
        terminalInfo.setModifyDate(DateUtil.getDateFormat(new Date(),DateUtil.FULL_TIME_SPLIT_PATTERN));
        terminalInfo.setModifyUser(m03.getCreator().toString());
        terminalInfoMapper.insertTerminal(terminalInfo);
        return Body.BODY_200;
    }
}
