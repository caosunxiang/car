package com.example.car.service.impl;

import com.example.car.common.utils.json.Body;
import com.example.car.mapper.mysql.DeviceAlarmSettingsMapper;
import com.example.car.service.IDeviceAlarmSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.Map;

/**
 * 设备报警配置表 Service实现
 *
 * @author 冷酷的苹果
 * @date 2020-06-18 10:51:07
 */
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "masterTransactionManager",propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceAlarmSettingsServiceImpl implements IDeviceAlarmSettingsService {

    private final DeviceAlarmSettingsMapper deviceAlarmSettingsMapper;


    @Override
    public Body selectAlarmById(Integer id) {
        List<Map<String,String>>list=this.deviceAlarmSettingsMapper.selectAlarmById(id);
        return Body.newInstance(list);
    }
}
