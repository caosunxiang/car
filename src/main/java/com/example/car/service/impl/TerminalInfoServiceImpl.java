/**
 * Copyright (C), 2020-2021, 众马科技有限公司
 * FileName: TerminalInfoServiceImpl
 * Author:   冷酷的苹果
 * Date:     2021/1/5 9:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.DateUtil;
import com.example.car.common.utils.json.Body;
import com.example.car.entity.DeviceInfo;
import com.example.car.entity.DeviceLasposition;
import com.example.car.entity.M03;
import com.example.car.entity.TerminalInfo;
import com.example.car.mapper.mysql.DeviceInfoMapper;
import com.example.car.mapper.mysql.DeviceLaspositionMapper;
import com.example.car.mapper.sqlserver.M03Mapper;
import com.example.car.mapper.sqlserver.TerminalInfoMapper;
import com.example.car.service.TerminalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2021/1/5
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "otherTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
public class TerminalInfoServiceImpl implements TerminalInfoService {

    private final TerminalInfoMapper terminalInfoMapper;

    private final DeviceInfoMapper deviceInfoMapper;

    private final DeviceLaspositionMapper deviceLaspositionMapper;

    private final M03Mapper m03Mapper;

    @Override
    public Body insertTerminalInfo() {
        List<DeviceInfo> deviceInfos = deviceInfoMapper.selectDeviceInfo();
        for (DeviceInfo deviceInfo : deviceInfos) {
            TerminalInfo terminalInfo = new TerminalInfo();
            terminalInfo.setCarNumber(deviceInfo.getCarnumber());
            terminalInfo.setCreateDate(deviceInfo.getCreate_date());
            terminalInfo.setCreateUser(deviceInfo.getCreate_user());
            terminalInfo.setInstallstatus(deviceInfo.getInstallstatus());
            terminalInfo.setInstalltime(deviceInfo.getInstalltime());
            terminalInfo.setIsDelete(deviceInfo.getIs_delete());
            terminalInfo.setModifyDate(deviceInfo.getModify_date());
            terminalInfo.setModifyUser(deviceInfo.getModify_user());
            terminalInfo.setTerminal(deviceInfo.getTerminal());
            terminalInfo.setTerminalType(deviceInfo.getDevicetype());
            List<M03> m03 = m03Mapper.selectM03(deviceInfo.getCarnumber(), null, null, null);
            if (m03.size() > 0) {
                terminalInfo.setCarId(m03.get(0).getRecId());
                terminalInfo.setDeptid(m03.get(0).getMustId());
            }
            terminalInfoMapper.insertTerminal(terminalInfo);
        }
        return Body.BODY_200;
    }

    @Override
    public Body selectTerminal(String deptid, String carId, String carNumber, String type) {
        List<TerminalInfo> terminalInfos = terminalInfoMapper.selectTerminal(carNumber, carId, deptid);
        if (type.equals("0")) {
            for (TerminalInfo terminalInfo : terminalInfos) {
                DeviceLasposition deviceLasposition =
                        deviceLaspositionMapper.selectLaspositionByCarNo(terminalInfo.getCarNumber());
                terminalInfo.setStatus(deviceLasposition == null ? "10" : deviceLasposition.getCarstatus().toString());
            }
            return Body.newInstance(terminalInfos);
        } else {
            List<TerminalInfo> list=new ArrayList<>();
            for (TerminalInfo terminalInfo : terminalInfos) {
                if (!StringUtils.isEmpty(terminalInfo.getTerminal())){
                    DeviceLasposition deviceLasposition =
                            deviceLaspositionMapper.selectLaspositionByCarNo(terminalInfo.getCarNumber());
                    terminalInfo.setStatus(deviceLasposition == null ? "10" : deviceLasposition.getCarstatus().toString());
                    list.add(terminalInfo);
                }
            }
            return Body.newInstance(list);
        }

    }

    @Override
    public Body insertTerminalInfo(String terminal, String terminalType, String createUser, String carId,
                                   String deptid, String installstatus, String installtime, String carNumber) {
        TerminalInfo terminalInfo = new TerminalInfo();
        terminalInfo.setCarNumber(carNumber);
        terminalInfo.setCreateDate(DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        terminalInfo.setCreateUser(createUser);
        terminalInfo.setInstallstatus(installstatus);
        terminalInfo.setInstalltime(installtime);
        terminalInfo.setIsDelete("0");
        terminalInfo.setModifyDate(DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        terminalInfo.setModifyUser(createUser);
        terminalInfo.setCarId(carId);
        terminalInfo.setDeptid(deptid);
        terminalInfo.setTerminal(terminal);
        terminalInfo.setTerminalType(terminalType);
        terminalInfoMapper.insertTerminal(terminalInfo);
        return Body.BODY_200;
    }

    @Override
    public Body updateTerminal(String modifyUser, String isDelete, String terminal, String terminalType,
                               String carNumber, Integer terminalId, String carid) {
        TerminalInfo terminalInfo = new TerminalInfo();
        terminalInfo.setTerminalType(terminalType);
        terminalInfo.setTerminal(terminal);
        terminalInfo.setModifyUser(modifyUser);
        terminalInfo.setIsDelete(isDelete);
        terminalInfo.setCarNumber(carNumber);
        terminalInfo.setModifyDate(DateUtil.getDateFormat(new Date(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        terminalInfo.setTerminalId(terminalId);
        terminalInfoMapper.updateTerminal(terminalInfo);
        M03 m03 = new M03();
        m03.setRecId(carid);
        m03.setM0331(carNumber);
        m03Mapper.updateM0331(m03);
        return Body.BODY_200;
    }

    @Override
    public Body deleteTerminal(Integer terminalId) {
        this.terminalInfoMapper.deleteTerminal(terminalId);
        return Body.BODY_200;
    }
}
