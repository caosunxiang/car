/**
 * Copyright (C), 2020-2020, 众马科技有限公司
 * FileName: DeviceAlarmSeverityImpl
 * Author:   冷酷的苹果
 * Date:     2020/7/1 15:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.entity.DeviceAlarmSeverity;
import com.example.car.entity.DeviceLasposition;
import com.example.car.entity.SysAuthDept;
import com.example.car.mapper.mysql.DeviceAlarmMapper;
import com.example.car.mapper.mysql.DeviceAlarmSeverityMapper;
import com.example.car.mapper.mysql.DeviceLaspositionMapper;
import com.example.car.mapper.mysql.SysAuthDeptMapper;
import com.example.car.service.DeviceAlarmSeverityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 冷酷的苹果
 * @create 2020/7/1
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "masterTransactionManager", propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceAlarmSeverityImpl implements DeviceAlarmSeverityService {

    @Autowired
    private DeviceAlarmSeverityMapper deviceAlarmSeverityMapper;
    @Autowired
    private DeviceAlarmMapper deviceAlarmMapper;
    @Autowired
    private DeviceLaspositionMapper laspositionMapper;
    @Autowired
    private SysAuthDeptMapper sysAuthDeptMapper;

    @Override
    public Body selectAlarmSeverity(String startTime, String number, String endTime, String name, Integer size) {
        if (StringUtils.isEmpty(size)) {
            size = 500;
        }
        List<Map<String, Object>> deviceAlarmSeverities = deviceAlarmSeverityMapper.selectAlarmSeverity(startTime,
                number, endTime, name, size);
        return Body.newInstance(deviceAlarmSeverities);
    }

    @Override
    public Body selectAlarmSeverityCount(String startTime, String endTime) {
        Integer count = deviceAlarmSeverityMapper.selectAlarmSeverityCount(startTime, endTime, "无准运证行驶");
        count += deviceAlarmMapper.selectAlarmCount(null, startTime, endTime, null, 2);
        count += deviceAlarmMapper.selectAlarmCount(null, startTime, endTime, null, 14);
        List<SysAuthDept> deptList = sysAuthDeptMapper.selectSysAuthDeptByParent(new Long("722445496500748288"));
        List<DeviceLasposition> deviceLaspositions = new ArrayList<>();
        for (SysAuthDept sysAuthDept : deptList) {
            List<DeviceLasposition> deviceLasposition =
                    laspositionMapper.selectLaspositionAlarm(sysAuthDept.getDeptid().toString());
            deviceLaspositions.addAll(deviceLasposition);
        }
        List<DeviceAlarmSeverity> alarmSeverity = new ArrayList<>();
        for (DeviceLasposition deviceLasposition : deviceLaspositions) {
            if (deviceLasposition.getCarstatus() == 2 || deviceLasposition.getCarstatus() == 1) {
                DeviceAlarmSeverity deviceAlarmSeverity = deviceAlarmSeverityMapper.selectAlarmSeverityTask(null,
                        deviceLasposition.getCarnumber(), null, "GPS不在线", null);
                if (!StringUtils.isEmpty(deviceAlarmSeverity)){
                    alarmSeverity.add(deviceAlarmSeverity);
                }
            }
        }
        count += alarmSeverity.size();
        return Body.newInstance(count);
    }

    @Override
    public Body selectAlarmSeverityValid(String startTime, String number, String endTime, String name) {
        List<DeviceAlarmSeverity> alarmSeverity = deviceAlarmSeverityMapper.selectAlarmSeverityValid(startTime, number,
                endTime, name);
        return Body.newInstance(alarmSeverity);
    }

    @Override
    public Body selectAlarmMuck(String number, String name) {
        List<SysAuthDept> deptList = sysAuthDeptMapper.selectSysAuthDeptByParent(new Long("722445496500748288"));
        List<DeviceLasposition> deviceLaspositions = new ArrayList<>();
        for (SysAuthDept sysAuthDept : deptList) {
            List<DeviceLasposition> deviceLasposition =
                    laspositionMapper.selectLaspositionAlarm(sysAuthDept.getDeptid().toString());
            deviceLaspositions.addAll(deviceLasposition);
        }
        List<DeviceAlarmSeverity> alarmSeverity = new ArrayList<>();
        if (name.equals("GPS不在线")) {
            for (DeviceLasposition deviceLasposition : deviceLaspositions) {
                if (deviceLasposition.getCarstatus() == 2 || deviceLasposition.getCarstatus() == 1) {
                    DeviceAlarmSeverity deviceAlarmSeverity = deviceAlarmSeverityMapper.selectAlarmSeverityTask(null,
                            deviceLasposition.getCarnumber(), null, "GPS不在线", null);
                    alarmSeverity.add(deviceAlarmSeverity);
                }
            }
            return Body.newInstance(alarmSeverity);
        } else {
            alarmSeverity = deviceAlarmSeverityMapper.selectAlarmMuck(number, name);
            return Body.newInstance(alarmSeverity);
        }
    }
}
